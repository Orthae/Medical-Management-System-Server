package orthae.com.github.medicalmanagementsystem.server.Repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import orthae.com.github.medicalmanagementsystem.server.entity.employee.Session;
import orthae.com.github.medicalmanagementsystem.server.repository.SessionRepository;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DisplayName("Session Hibernate Repository Tests")
class SessionHibernateRepositoryTest {

    @Autowired
    private SessionRepository sessionRepository;

    @BeforeAll
    static void timezoneSetup() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Nested
    @DisplayName("Basic CRUD Tests")
    @Transactional
    @SpringBootTest
    class BasicCrud {

        @Test
        void findByEmployeeId() {
            List<Session> list = sessionRepository.findEmployeeSessions(1);
            assertEquals(5, list.size());

            Session s0 = list.get(0);
            assertEquals(4, s0.getId());
            assertEquals(1, s0.getEmployee().getId());
            assertEquals("10.10.10.1", s0.getIpAddress());
        }

        @Test
        void findByToken(){
            Session session = sessionRepository.find("Kz9b9a5QPcSzZNTJANchpL388GsB62KHcNLmfM9SqFCGbaJmX4wpk28qzKqgCBCbsnB8AazvnsRfHaPKHgv9BXFBZdK3Trg3bQbzqSgEBbKKtsSv84BJTBKVfTE87SZj");

            assertEquals(3, session.getId());
            assertEquals(15, session.getEmployee().getId());
            assertEquals("15.16.17.18", session.getIpAddress());
            assertEquals("Kz9b9a5QPcSzZNTJANchpL388GsB62KHcNLmfM9SqFCGbaJmX4wpk28qzKqgCBCbsnB8AazvnsRfHaPKHgv9BXFBZdK3Trg3bQbzqSgEBbKKtsSv84BJTBKVfTE87SZj", session.getSessionToken());
            assertFalse(session.getSessionCreation().after(new Date()));
            assertTrue(session.getSessionExpiry().after(new Date()));
        }

    }

    @Nested
    @DisplayName("Utility Tests")
    @Transactional
    @SpringBootTest
    class Utility {

        @Test
        void invalidateSessionById() {
            sessionRepository.invalidateSession(1);
            Session session = sessionRepository.find("vHf2ugmrmvkZHZKCzWvK6r6UssZG8be6jZdxX9uzgMpsg72EufbCVeVtPAZwCtHxs8ZQ9X9vSXnUZcHQpqUFXqyykLZUsQJ2kWmPCNpPXGEZCzGT5sfCUQn3QyvbWaDe");
            System.out.println(session.getSessionExpiry().toString());
            assertTrue(session.getSessionExpiry().before(new Date()));
        }

    }


}
