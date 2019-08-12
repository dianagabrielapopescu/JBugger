package userservlet;

import ro.msg.edu.jbugs.exception.BusinessException;
import ro.msg.edu.jbugs.manager.impl.BugManager;
import ro.msg.edu.jbugs.manager.impl.CommentManager;
import ro.msg.edu.jbugs.dto.BugDTO;
import ro.msg.edu.jbugs.dto.UserDTO;
import ro.msg.edu.jbugs.manager.remote.BugManagerRemote;
import ro.msg.edu.jbugs.manager.remote.CommentManagerRemote;
import ro.msg.edu.jbugs.manager.remote.UserManagerRemote;
import ro.msg.edu.jbugs.service.ScheduledService;
import ro.msg.edu.jbugs.manager.impl.UserManager;

import javax.ejb.EJB;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;

/**
 * Document me.
 *
 * @author msg systems AG; UserModel Name.
 * @since 19.1.2
 */
@WebServlet("/testservlet")
public class UserResource {
    @EJB
    private ScheduledService scheduledService;
    @EJB
    private UserManagerRemote userManager;
    @EJB
    private BugManagerRemote bugManager;
    @EJB
    private CommentManagerRemote commentManager;

    private UserDTO userDTO, userDTO2;
    private BugDTO bugDTO, bugDTO2;
    private String message;

    public void init(){

        message = "hello w!";

        userDTO = new UserDTO();
        userDTO.setCounter(0);
        userDTO.setEmail("altceva@gmail.com");
        userDTO.setFirstName("diana");
        userDTO.setLastName("popescu");
        userDTO.setMobileNumber("0040777");
        userDTO.setPassword("mypassword");
       // userDTO.setUsername("");
        userDTO.setStatus(0);

        userDTO2 = new UserDTO();
        userDTO2.setCounter(3);
        userDTO2.setEmail("altcevaceva@gmail.com");
        userDTO2.setFirstName("diana");
        userDTO2.setLastName("popescu");
        userDTO2.setMobileNumber("0040777");
        userDTO2.setPassword("dsd");
        //userDTO2.setUsername("dfwec");
        userDTO2.setStatus(1);
/*
        bugDTO = new BugDTO();
        bugDTO.setTitle("omgg");
        bugDTO.setDescription("it happended by night");
        bugDTO.setSeverity("very imp");
        bugDTO.setTargetDate(new Date(Calendar.getInstance().getTime().getTime()));
        bugDTO.setFixedVersion("2.0.0");
        bugDTO.setVersion("1.0.0");
        bugDTO.setStatus("resolved");
        bugDTO.setAssignedToUser(userDTO);
        bugDTO.setCreatedByUser(userDTO);

        bugDTO2 = new BugDTO();
        bugDTO2.setTitle("omgg2");
        bugDTO2.setDescription("it happended by day");
        bugDTO2.setSeverity("very imp");
        bugDTO2.setTargetDate(new Date(Calendar.getInstance().getTime().getTime()));
        bugDTO2.setFixedVersion("3.1.0");
        bugDTO2.setVersion("1.1.0");
        bugDTO2.setStatus("in progress");
        bugDTO2.setAssignedToUser(userDTO);
        bugDTO2.setCreatedByUser(userDTO);
  */
    }
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("text/html");

        PrintWriter out = httpServletResponse.getWriter();
        out.println("<h1>" + message + "</h1>");

        out.println(userDTO.toString());
        out.println("<br>");
        userDTO = userManager.insert(userDTO);

        out.println("<br>");
/*
        out.println(userDTO2.toString());
        out.println("<br>");
        userDTO2 = userManager.insert(userDTO2);

        out.println("<br>");
*/
        out.println(userDTO.toString());

        out.println("<br>");

        try {
            userDTO = userManager.login("popesd", "mypassword");
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        out.println(userDTO.toString());
        out.println("<br>");

        try {
            userDTO = userManager.login("popesd", "myWRONGpassword");
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        out.println(userDTO.toString());
        out.println("<br>");

        //out.println(userDTO2.toString());

        try {
            String text = httpServletRequest.getParameter("text") != null ? httpServletRequest.getParameter("text") : "hello world!";
            Context initialContext = new InitialContext();

            ConnectionFactory connectionFactory =
                    (ConnectionFactory) initialContext.lookup("java:comp/DefaultJMSConnectionFactory");
            Queue queue = (Queue) initialContext.lookup("myqueue");

            Connection connection = connectionFactory.createConnection();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = session.createProducer(queue);

            connection.start();

            TextMessage message = session.createTextMessage(text);
            publisher.send(message);

        } catch (NamingException e){
            System.out.println(e.getMessage());
        } catch (JMSException e){
            System.out.println(e.getMessage());
        }
        httpServletResponse.getWriter().println("Message Sent");

        //out.println(bugDTO.toString());
        //bugManager.insert(bugDTO);
        //out.println(bugDTO2.toString());
        //bugManager.insert(bugDTO2);

        //out.println("user with id 1 is:");
/*
        out.println(userDTO.getID());
        //out.println(userDTO2.getID());


        UserDTO varUserDTO = userManager.find(userDTO.getID());
        out.println(varUserDTO.toString());

        out.println("<br>");

        UserDTO varUserDTO2 = userManager.find(userDTO2.getID());
        out.println(varUserDTO2.toString());
*/
        /*
        BugDTO bugDTO3 = new BugDTO();
        bugDTO3.setTitle("insertedAfterFind");
        bugDTO3.setDescription("a");
        bugDTO3.setSeverity("b");
        bugDTO3.setTargetDate(new Date(Calendar.getInstance().getTime().getTime()));
        bugDTO3.setFixedVersion("2.0.0");
        bugDTO3.setVersion("1.0.0");
        bugDTO3.setStatus("c");
        bugDTO3.setAssignedToUser(userDTO1);
        bugDTO3.setCreatedByUser(userDTO1);

        bugManager.insert(bugDTO3);
        out.println(bugDTO3.toString());
         */
        /*
        //out.println("list of bugs:");
        List<BugDTO> bugDTOs = bugManager.findAllCreatedByUser(userDTO1);
        bugDTOs.forEach(b -> {
            out.println(b.toString());
            out.println();});
        */

  /*
        Map<UserDTO, Integer> userDTOsCountCreatedBugsMap
                = userManager.getAllUsersCountCreatedBugs();
        userDTOsCountCreatedBugsMap.forEach((userDTO, count) ->
                out.println("id: " + userDTO.getID() + ", bugs created: " + count));
*/

        //Integer del = commentManager.deleteOlderComments();
        //Integer upd = bugManager.updateStatusByTargetDate();

        /*
        out.println(del);
        out.println(upd);
        MailService.sendEmail(del, upd);
*/
        //userManager.findAll().forEach(thisuser -> out.println(thisuser.toString()));
    }
    public void destroy(){

    }
}
