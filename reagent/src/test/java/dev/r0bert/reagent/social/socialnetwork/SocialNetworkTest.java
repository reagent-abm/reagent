package dev.r0bert.reagent.social.socialnetwork;

import dev.r0bert.reagent.core.agents.Agent;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SocialNetworkTest {
    @Test
    public void addEdge_NoWeight_CallsWithWeight1() {
        var socialNetwork = mock(SocialNetwork.class);
        var source = mock(Agent.class);
        var target = mock(Agent.class);
        doCallRealMethod().when(socialNetwork).addEdge(source, target);
        doNothing().when(socialNetwork).addEdge(source, target, 1.0);
        socialNetwork.addEdge(source, target);
        verify(socialNetwork, times(1)).addEdge(source, target, 1.0);
    }

    @Test
    public void setWeight_NoWeight_CallsWithWeight1() {
        var socialNetwork = mock(SocialNetwork.class);
        var source = mock(Agent.class);
        var target = mock(Agent.class);
        doCallRealMethod().when(socialNetwork).setWeight(source, target);
        doNothing().when(socialNetwork).setWeight(source, target, 1.0);
        socialNetwork.setWeight(source, target);
        verify(socialNetwork, times(1)).setWeight(source, target, 1.0);
    }
}