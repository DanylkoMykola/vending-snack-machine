package danylko.vendingsnackmachine.service;

import danylko.vendingsnackmachine.entity.Product;
import danylko.vendingsnackmachine.entity.Purchase;
import danylko.vendingsnackmachine.repo.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceImplTest {

    private PurchaseService purchaseService;
    Purchase purchase;
    Purchase purchaseFromDB;
    List<Purchase> purchasesYearMonth;
    List<Purchase> purchasesFullDate;
    Map<Product, Integer> reportFullDate;
    Map<Product, Integer> reportYearMonth;

    @Mock
    private PurchaseRepository repository;

    @BeforeEach
    void init() {
        Product product1 = new Product(1L,"Sweets", 55.5, 2);
        Product product2 = new Product(1L, "Candy", 40.5, 2);
        Product product3 = new Product(1L, "Snack", 54.0, 2);

        purchaseService = new PurchaseServiceImpl(repository);
        purchase = new Purchase(product1, LocalDate.of(2021,5, 15));

        purchaseFromDB = new Purchase(1L, product1, LocalDate.of(2021,5, 15));
        Purchase purchaseFromDB1 = new Purchase(2L, product2, LocalDate.of(2021,5, 10));
        Purchase purchaseFromDB2 = new Purchase(3L, product3, LocalDate.of(2021,5, 10));
        Purchase purchaseFromDB3 = new Purchase(4L, product3, LocalDate.of(2021,5, 15));

        purchasesFullDate = new ArrayList<>();
        Collections.addAll(purchasesFullDate,
                purchaseFromDB1,
                purchaseFromDB2);
        purchasesYearMonth = new ArrayList<>();
        Collections.addAll(purchasesYearMonth,
                purchaseFromDB,
                purchaseFromDB1,
                purchaseFromDB2,
                purchaseFromDB3);
        reportFullDate = new TreeMap<>();
        reportFullDate.put(product2, 1);
        reportFullDate.put(product3, 1);

        reportYearMonth = new TreeMap<>();
        reportYearMonth.put(product1, 1);
        reportYearMonth.put(product2, 1);
        reportYearMonth.put(product3, 2);


    }

    @Test
    void shouldSavePurchase() {
        when(repository.save(purchase)).thenReturn(purchaseFromDB);
        Purchase purchaseTest = purchaseService.save(purchase);
        verify(repository, times(1)).save(purchase);
        assertEquals(purchaseTest.getId(), 1L);
    }

    @Test
    void shouldReturnReportByDate() {
        LocalDate date = LocalDate.of(2021, 6,10);
        when(repository.findPurchaseByDate(date)).thenReturn(purchasesFullDate);
        Map<Product, Integer> reportTest = purchaseService.getReportByDate(date);
        verify(repository, times(1)).findPurchaseByDate(date);
        assertTrue(reportFullDate.equals(reportTest));
    }

    @Test
    void shouldReturnReportByYearMonth() {
        YearMonth date = YearMonth.of(2021, 6);
        when(repository.findPurchasesByDateBetween(date.atDay(1), date.atEndOfMonth())).thenReturn(purchasesYearMonth);
        Map<Product, Integer> reportTest = purchaseService.getReportByYearMonth(date);
        verify(repository, times(1)).findPurchasesByDateBetween(date.atDay(1), date.atEndOfMonth());
        assertTrue(reportYearMonth.equals(reportTest));
    }
}