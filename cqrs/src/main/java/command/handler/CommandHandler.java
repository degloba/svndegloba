/**
 * 
 */
package command.handler;

import command.Command;

/**
 * 
 * @author Slawek
 *
 * @param <C> command
 * @param <R> result type - for asynchronous {@link Command}commands (asynchronous=true) should be {@link Void}
 */
public interface CommandHandler<C, R> {

    public R handle(C command);
}
