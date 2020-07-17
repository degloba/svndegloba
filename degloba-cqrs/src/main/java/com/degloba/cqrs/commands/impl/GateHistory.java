package com.degloba.cqrs.commands.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.degloba.cqrs.commands.annotations.ICommandAnnotation;

/**
 * Gestiona l’historial d’execució de {@link ICommandAnnotation} basant-se en els atributs d’anotacions (java.lang.annotation)<br>
 * Les ordres "anotades" com unique = true s’emmagatzemen en aquest historial
 * <ul>
 * <li> La història comprova si la mateixa {@link ICommandAnnotation} es cridada una altra vegada. <br>
 * </li>
 * <li>
 * Cada tipus (classe) de {@link ICommandAnnotation} té les seves pròpies entrades en l’historial: la longitud del històric es pot parametritzar a través del paràmetre del constructor.
 * </li>
 * </ul>
 * 
 * @author degloba
 * 
 */
class GateHistory {

	@SuppressWarnings("serial")
	// TODO Sprawdzic czy nie musi byc concurrent (history jest, na tym
	// poziomie nie musi byc totalnej synchronizacji, to tylko rodzaj
	// cache)
	private class CommandExecutionsMap extends LinkedHashMap<Object, Date> {
		protected boolean removeEldestEntry(Map.Entry<Object, Date> eldest) {
			return this.size() > maxHistoryCapacity;
		}
	};

	private static final int DEFAULT_MAX_HISTORY_CAPACITY = 3;

	/**
	 * Model de l'històric. Cada tipus (classe) de {@link ICommandAnnotation} té un {@link Map} d'execucions (instància de la {@link ICommandAnnotation}
	 * i data)
	 */
	@SuppressWarnings("rawtypes")
	private Map<Class, CommandExecutionsMap> history = new ConcurrentHashMap<Class, CommandExecutionsMap>();

	private int maxHistoryCapacity;

	public GateHistory(int maxHistoryCapacity) {
		this.maxHistoryCapacity = maxHistoryCapacity;
	}

	public GateHistory() {
		this(DEFAULT_MAX_HISTORY_CAPACITY);
	}

	/**
	 * 
	 * @param command
	 * @return true si la {@link ICommandAnnotation} no és una repetició, false si {@link ICommandAnnotation} és una
	 *         repetició i no s’hauria d’executar ara
	 */
	public boolean register(Object command) {
		if (!isUnique(command))
			return true;

		Date lastRun = getFromHistory(command);

		// update history
		Date now = new Date();
		addToHistory(command, now);

		// first run, so go
		if (lastRun == null)
			return true;

		long uniqueStorageTimeout = getUniqueStorageTimeout(command);
		// no timeout so by default it is duplicated
		if (uniqueStorageTimeout == 0)
			return false;

		long milisFromLastRun = now.getTime() - lastRun.getTime();
		return milisFromLastRun > uniqueStorageTimeout;
	}

	private boolean isUnique(Object command) {
		if (!command.getClass().isAnnotationPresent(ICommandAnnotation.class))
			return false;

		ICommandAnnotation commandAnnotation = command.getClass().getAnnotation(ICommandAnnotation.class);

		return commandAnnotation.unique();
	}

	private Long getUniqueStorageTimeout(Object command) {
		ICommandAnnotation commandAnnotation = command.getClass().getAnnotation(ICommandAnnotation.class);
		return commandAnnotation.uniqueStorageTimeout();
	}

	private Date getFromHistory(Object command) {
		Map<Object, Date> executions = history.get(command.getClass());
		if (executions == null)
			return null;
		return executions.get(command);
	}

	private void addToHistory(Object command, Date executeDate) {
		CommandExecutionsMap executions = history.get(command.getClass());
		if (executions == null) {
			executions = new CommandExecutionsMap();
			history.put(command.getClass(), executions);
		}
		executions.put(command, executeDate);
	}
}
