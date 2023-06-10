package ru.pda.cloudservice.repositorys;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.pda.cloudservice.entitys.UserFile;
import ru.pda.cloudservice.services.CloudService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RepositoryTest {
    @InjectMocks
    private CloudService cs;
    @Mock
    private UserRepository ur;
    @Mock
    private FileRepository fr;

    @BeforeEach
    void setUp() {
        Mockito.when(ur.findByUsername("user@home.ru").getId()).thenReturn(1L);
        Mockito.when(fr.save(new UserFile(1L, "test.txt", "Hello World".getBytes()))).thenReturn(new UserFile(1L, "test.txt", "Hello World".getBytes()));
    }

    @Test
    void testUser () {
        Mockito.when(ur.findByUsername("user@home.ru").getId()).thenReturn(1L);

    }
}
