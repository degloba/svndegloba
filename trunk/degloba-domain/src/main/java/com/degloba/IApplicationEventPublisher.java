package com.degloba;


public interface IApplicationEventPublisher<T> {
    void publish(T event);
}   
    
/*public interface IApplicationEventPublisher {
    void publish(Serializable applicationEvent);
}*/
