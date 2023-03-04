package dev.r0bert.reagent.social.simulationmanager;

import dev.r0bert.reagent.core.agents.Agent;
import dev.r0bert.reagent.social.socialnetwork.SocialNetwork;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SocialSimulationManagerTest {
    @Test
    public void constructor_Always_AssignsCorrectly() {
        var startTime = ZonedDateTime.now();
        var endTime = ZonedDateTime.now();
        var socialNetwork = mock(SocialNetwork.class);

        var simulationManager = new SocialSimulationManager(startTime, endTime, socialNetwork);

        assertEquals(startTime, simulationManager.getStartTime());
        assertEquals(endTime, simulationManager.getEndTime());
        assertEquals(socialNetwork, simulationManager.getSocialNetwork());
    }

    @Test
    public void addAgent_Always_AddsAgentToSocialNetwork() {
        var startTime = ZonedDateTime.now();
        var endTime = ZonedDateTime.now();
        var socialNetwork = mock(SocialNetwork.class);
        var simulationManager = new SocialSimulationManager(startTime, endTime, socialNetwork);
        var agent = mock(Agent.class);

        simulationManager.addAgent(agent);

        verify(socialNetwork, times(1)).addAgent(agent);
    }

    @Test
    public void toString_Always_ReturnsCorrectRepresentation() throws IllegalAccessException {
        var startTime = ZonedDateTime.now();
        var endTime = startTime.plusMonths(3);
        var socialNetwork = mock(SocialNetwork.class);
        var simulationManager = new SocialSimulationManager(startTime, endTime, socialNetwork);
        var currentTime = startTime.plusMonths(2);
        FieldUtils.writeField(simulationManager, "currentTime", currentTime, true);

        var expected = "SocialSimulationManager{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", currentTime=" + currentTime +
                "}";
        assertEquals(expected, simulationManager.toString());
    }
}