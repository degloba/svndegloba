package com.degloba.domain.event;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import com.degloba.domain.InstanceFactory;
import com.degloba.utils.DateUtils;
import com.degloba.utils.ObjectSerializer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;


public class StoredEventTest {

    private StoredDomainEvent instance;

    private String typeName = DomainEventSub.class.getName();

    private DomainEventSub event = event();

    @Mock
    private ObjectSerializer serializer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(serializer.deserialize("theEventBody", DomainEventSub.class)).thenReturn(event);
        when(serializer.serialize(event)).thenReturn("theEventBody");
        InstanceFactory.bind(ObjectSerializer.class, serializer);
    }

    @Test
    public void toDomainEvent() {
        instance = StoredDomainEvent.fromDomainEvent(event);
        DomainEventSub event1 = instance.toDomainEvent();
        assertThat(event1.getId(), is(event.getId()));
        assertThat(event1.getOccurredOn(), is(event.getOccurredOn()));
        assertThat(event1.getProp1(), is(event.getProp1()));
        assertThat(event1.getVersion(), is(event.getVersion()));
        assertNull(event1.getProp2());
    }

    @Test
    public void fromDomainEvent() {
        instance = StoredDomainEvent.fromDomainEvent(event);
        assertThat(instance.getEventId(), is(event.getId()));
        assertThat(instance.getOccurredOn(), is(event.getOccurredOn()));
        assertThat(instance.getTypeName(), is(event.getClass().getName()));
        assertThat(instance.getEventBody(), is("theEventBody"));
    }

    private DomainEventSub event() {
        Date occurredOn = DateUtils.date(2002, 4, 11);
        DomainEventSub event = new DomainEventSub(occurredOn, 1);
        event.setId("anId");
        event.setProp1("abc");
        return event;
    }
}
