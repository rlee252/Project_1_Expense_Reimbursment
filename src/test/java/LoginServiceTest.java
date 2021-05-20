
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.DAO.UserDAO;
import com.revature.dto.LoginDTO;
import com.revature.exception.BadParameterException;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.DatabaseException;
import com.revature.exception.LoginException;
import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.service.LoginService;

public class LoginServiceTest {
	
	private static UserDAO mockUserDAO;
	private static LoginDTO mockLoginDTO;
	
	public LoginService loginService;
	@BeforeClass
	public static void setUp() {
		mockUserDAO = mock(UserDAO.class);
		mockLoginDTO = mock(LoginDTO.class);
		LoginDTO tester = new LoginDTO("user","password");
		when(mockUserDAO.login(argThat(tester)))
		.thenReturn(new User(1,new UserRoles(1,"employee"),"user","password","firstName","lastname","email"));;
	}
	
	private static Object argThat(LoginDTO tester) {
		
		return new LoginDTO("user","password");
	}
	@Before
	public void beforeEachTest() {
		loginService = new LoginService(mockUserDAO);
	}
	@Test
	public void test_Login() throws NoSuchAlgorithmException, BadParameterException, LoginException, ClientNotFoundException, DatabaseException, SQLException {
		loginService.login(new LoginDTO("user","password"));
	}

}
