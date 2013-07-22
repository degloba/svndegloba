package infraestructura.sagas;

public interface SagaEngine {

    void handleSagasEvent(Object event);
}
