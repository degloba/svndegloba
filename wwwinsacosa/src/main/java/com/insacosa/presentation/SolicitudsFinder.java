package com.insacosa.presentation;

import java.util.List;

public interface SolicitudsFinder {
    /**
     * Method for orders manager.
     */
    List<SolicitudsListItemDto> findSolicituds();

    /**
     * Method for customers that want to find their own orders.
     */
    SolicitudsListItemDto getSolicitudsClient(Long clientId);
}
