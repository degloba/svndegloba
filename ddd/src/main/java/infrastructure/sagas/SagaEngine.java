package infrastructure.sagas;

public interface SagaEngine {

    void handleSagasEvent(Object event);
}
