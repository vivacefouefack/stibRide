package g54490.atlg4.stib.repository;

import g54490.atlg4.stib.dto.StationDto;
import g54490.atlg4.stib.jdbc.StationDao;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author 54490@etu.he2b.be
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class StationRepositoryTest {
    
    @Mock
    private StationDao mock;
    private final StationDto merodeStatin;
    private final StationDto bellelavieStion;

    public StationRepositoryTest() {
        merodeStatin=new StationDto(8072,"MERODE");
        bellelavieStion=new StationDto(1510,"BELLELAVIE");
    }

    @BeforeEach
    void init() throws Exception {
        System.out.println("==== BEFORE EACH =====");
        Mockito.lenient().when(mock.select(merodeStatin.getKey())).thenReturn(null);
        Mockito.lenient().when(mock.select(bellelavieStion.getKey())).thenReturn(null);
    }

    @Test
    public void testGetExist() throws Exception {
        System.out.println("testGetExist");
        StationDto expected = merodeStatin;
        StationRepository repository = new StationRepository(mock);
        StationDto result = repository.get(8072);
        assertEquals(expected, result);
        Mockito.verify(mock, times(1)).select(8072);
    }
    
}
