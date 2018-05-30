package service;

import beans.PublicationPeriodicyCost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PublicationPeriodicityCostServiceTest {
//    @Mock
//    private Connection connection;
//
//    @Mock
//    private PublicationPeriodicityCostDao publicationCostDao;

    @Mock
    private PublicationPeriodicityCostService publicationCostServiceMoc;

    private PublicationPeriodicyCost cost = new PublicationPeriodicyCost.Builder().setId(1).setTimesPerYear(1).setCost(1).build();
    private PublicationPeriodicyCost cost1 = new PublicationPeriodicyCost.Builder().setId(2).setTimesPerYear(2).setCost(2).build();
    private PublicationPeriodicyCost cost2 = new PublicationPeriodicyCost.Builder().setId(3).setTimesPerYear(3).setCost(3).build();

    @Test
    public void getPubPeriodicyCost() {
        when(publicationCostServiceMoc.getPubPeriodicyCost(1)).thenReturn(cost);
        PublicationPeriodicyCost costTest = publicationCostServiceMoc.getPubPeriodicyCost(1);
        verify(publicationCostServiceMoc).getPubPeriodicyCost(1);
        assertEquals(cost, costTest);
        assertNotEquals(cost1, costTest);
    }
}