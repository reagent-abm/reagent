package dev.r0bert.reagent.social.messages.socialmessage;

import dev.r0bert.reagent.core.messages.Message;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SocialMessageTest {
    @Test
    public void constructor_NoWeight_Assigns1() {
        var destination = UUID.randomUUID();
        var sender = UUID.randomUUID();
        var uuid = UUID.randomUUID();
        var payload = mock(Message.class);
        var socialMessage = new SocialMessage(destination, sender, uuid, payload);
        assertEquals(1, socialMessage.getWeight());
        assertEquals(destination, socialMessage.getDestination());
        assertEquals(sender, socialMessage.getSender());
        assertEquals(uuid, socialMessage.getUUID());
    }

    @Test
    public void constructor_NoWeightNullUUID_AssignsRandomUUID() {
        var destination = UUID.randomUUID();
        var sender = UUID.randomUUID();
        var payload = mock(Message.class);
        var socialMessage1 = new SocialMessage(destination, sender, null, payload);
        assertEquals(1, socialMessage1.getWeight());
        assertEquals(destination, socialMessage1.getDestination());
        assertEquals(sender, socialMessage1.getSender());
        assertEquals(payload, socialMessage1.getPayload());

        var socialMessage2 = new SocialMessage(destination, sender, null, payload);
        assertEquals(1, socialMessage2.getWeight());
        assertEquals(destination, socialMessage2.getDestination());
        assertEquals(sender, socialMessage2.getSender());
        assertEquals(payload, socialMessage2.getPayload());
        assertNotEquals(socialMessage1.getUUID(), socialMessage2.getUUID());
    }

    @Test
    public void constructor_WithWeightAndUUID_AssignsCorrectly() {
        var destination = UUID.randomUUID();
        var sender = UUID.randomUUID();
        var uuid = UUID.randomUUID();
        var payload = mock(Message.class);
        var socialMessage = new SocialMessage(destination, sender, uuid, payload, 0.5);
        assertEquals(0.5, socialMessage.getWeight());
        assertEquals(destination, socialMessage.getDestination());
        assertEquals(sender, socialMessage.getSender());
        assertEquals(uuid, socialMessage.getUUID());
        assertEquals(payload, socialMessage.getPayload());
    }

    @Test
    public void constructor_WithWeightButNullUUID_AssignsRandomUUID() {
        var destination = UUID.randomUUID();
        var sender = UUID.randomUUID();
        var payload = mock(Message.class);
        var socialMessage1 = new SocialMessage(destination, sender, null, payload, 0.5);
        assertEquals(0.5, socialMessage1.getWeight());
        assertEquals(destination, socialMessage1.getDestination());
        assertEquals(sender, socialMessage1.getSender());
        assertEquals(payload, socialMessage1.getPayload());

        var socialMessage2 = new SocialMessage(destination, sender, null, payload, 0.25);
        assertEquals(0.25, socialMessage2.getWeight());
        assertEquals(destination, socialMessage2.getDestination());
        assertEquals(sender, socialMessage2.getSender());
        assertEquals(payload, socialMessage2.getPayload());
        assertNotEquals(socialMessage1.getUUID(), socialMessage2.getUUID());
    }

    @Test
    public void toString_Always_ReturnsCorrectRepresentation() {
        var destination = UUID.randomUUID();
        var sender = UUID.randomUUID();
        var uuid = UUID.randomUUID();
        var payload = mock(Message.class);
        when(payload.toString()).thenReturn("payload");
        var socialMessage = new SocialMessage(destination, sender, uuid, payload, 0.5);
        assertEquals("SocialMessage{destination=" + destination + ", sender=" + sender + ", uuid=" + uuid + ", " +
                "payload=" + payload + ", weight=0.5}", socialMessage.toString());
    }
}