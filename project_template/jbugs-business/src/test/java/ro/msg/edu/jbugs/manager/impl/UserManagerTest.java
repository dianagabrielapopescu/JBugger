package ro.msg.edu.jbugs.manager.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ro.msg.edu.jbugs.dao.UserDAO;
import ro.msg.edu.jbugs.dto.UserDTO;
import ro.msg.edu.jbugs.entity.User;
import ro.msg.edu.jbugs.exception.BusinessException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Document me.
 *
 * @author msg systems AG; UserModel Name.
 * @since 19.1.2
 */
@RunWith(MockitoJUnitRunner.class)
public class UserManagerTest {
    @InjectMocks
    private UserManager userManager;

    @Mock
    private UserDAO userDAO;

    public UserManagerTest() {
        this.userManager = new UserManager();
    }

    @Test
    public void generateUsername() throws Exception {
        /*
        when(userDAO.isUsernameUnique(anyString()))
                .thenReturn(true);
        */
        when(userDAO.isUsernameUnique("popesd")).thenReturn(true);
        when(userDAO.isUsernameUnique("baloz")).thenReturn(true);

        assertEquals("popesd", userManager.generateUsername("Diana", "Popescu"));
        assertEquals("baloz", userManager.generateUsername("Zsolt", "Balo"));

        verify(userDAO, times(2)).isUsernameUnique(anyString());
    }

    @Ignore
    @Test(expected = Exception.class)
    public void checkUsernameUnique() throws Exception {
        when(userDAO.isUsernameUnique("baloz")).thenReturn(false);

        assertEquals("baloz", userManager.generateUsername("Zsolt", "Balo"));
    }


    @Test
    public void checkGeneratedUsernameConflict() {
        when(userDAO.isUsernameUnique("baloz")).thenReturn(false);
        when(userDAO.isUsernameUnique("balozs")).thenReturn(false);
        when(userDAO.isUsernameUnique("balozso")).thenReturn(false);
        when(userDAO.isUsernameUnique("balozsol")).thenReturn(false);
        when(userDAO.isUsernameUnique("balozsolt")).thenReturn(false);
        when(userDAO.isUsernameUnique("balozsoltx")).thenReturn(false);
        when(userDAO.isUsernameUnique("balozsoltxx")).thenReturn(true);

        assertEquals("balozsoltxx", userManager.generateUsername("Zsolt", "Balo"));
    }

    @Test(expected = BusinessException.class)
    public void login() throws BusinessException {
        when(userDAO.find("popesd", "mypassword")).thenThrow(BusinessException.class);

        userManager.login("popesd", "mypassword");
    }
    @Test
    public void login2() throws BusinessException {
        when(userDAO.find("popesd", "mypassword")).thenReturn(createUser());
        UserDTO userDTO = userManager.login("popesd", "mypassword");

        assertEquals((Integer) 1, userDTO.getID());
        assertEquals("Diana", userDTO.getFirstName());
        assertEquals("Popescu", userDTO.getLastName());
        assertEquals("popesd", userDTO.getUsername());
        assertEquals("diana.popescu@msggroup.ro", userDTO.getEmail());
        assertEquals((Integer)1, userDTO.getCounter());
        assertEquals("0040747163309", userDTO.getMobileNumber());
        assertEquals("mypassword", userDTO.getPassword());//not needed
    }
    private User createUser(){
        User user = new User();
        user.setID(1);
        user.setFirstName("Diana");
        user.setLastName("Popescu");
        user.setEmail("diana.popescu@msggroup.ro");
        user.setCounter(1);
        user.setMobileNumber("0040747163309");
        user.setPassword("mypassword");
        user.setUsername("popesd");

        return user;
    }
}
