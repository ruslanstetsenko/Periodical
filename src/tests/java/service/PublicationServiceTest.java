package service;

import beans.Publication;
import dao.DaoFactory;
import dao.interfaces.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PublicationServiceTest {
    @Mock
    private PublicationService publicationServiceMoc;

//    @Mock
//    private PublicationTypeDao publicationTypeDaoMoc;
//
//    @Mock
//    private PublicationThemeDao publicationThemeDaoMoc;
//
//    @Mock
//    private PublicationStatusDao publicationStatusDaoMoc;
//
//    @Mock
//    private PublicationPeriodicityCostDao publicationPeriodicityCostDaoMoc;
//
//    @Mock
//    private SubscriptionBillDao subscriptionBillDaoMoc;

    private Publication publication = new Publication.Builder()
            .setId(1).setName("Name").setIssnNumber(11111111)
            .setWebsite("www1").setRegistrationDate(Date.valueOf(LocalDate.now()))
            .setPublicationStatusId(1).setPublicationThemeId(1).setPublicationTypeId(1).build();
    private Publication publication1 = new Publication.Builder()
            .setId(2).setName("Name1").setIssnNumber(22222222)
            .setWebsite("www1").setRegistrationDate(Date.valueOf(LocalDate.now()))
            .setPublicationStatusId(1).setPublicationThemeId(2).setPublicationTypeId(2).build();
    private Publication publication2 = new Publication.Builder()
            .setId(3).setName("Name2").setIssnNumber(33333333)
            .setWebsite("www2").setRegistrationDate(Date.valueOf(LocalDate.now()))
            .setPublicationStatusId(1).setPublicationThemeId(3).setPublicationTypeId(3).build();

    @Test
    public void getPublication() {
        when(publicationServiceMoc.getPublication(1)).thenReturn(publication);
        Publication publicationTest = publicationServiceMoc.getPublication(1);
        verify(publicationServiceMoc).getPublication(1);
        assertEquals(publication, publicationTest);
        assertNotEquals(publication1, publicationTest);
        //exceptions
    }

    @Test
    public void createPublication() {

//        when(publicationServiceMoc.createPublication(pubName, issn, website, setDate, pubType, pubStatus, pubTheme, cost1M, cost3M, cost6M, cost12M)).then(publication);
//        createPublication()
    }

    @Test
    public void updatePublication() {
    }

    @Test
    public void aboutPublication() {
    }

    @Test
    public void editPublication() {
    }

    @Test
    public void getAllPublication() {
    }

    @Test
    public void getPubThemesAndTypes() {
    }

    @Test
    public void getSelectedPublication() {
    }

    @Test
    public void selectPublicationsByTypeByTheme() {
    }

    @Test
    public void getPublicationWithCosts() {
    }
}