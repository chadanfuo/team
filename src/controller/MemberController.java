package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anno.ActionAnnotation;
import anno.RequestMapping;
import anno.RequestMapping.RequestMethod;
/*import dao.MemberDao;*/
import dao.MybatisMemberDao;
import excep.DuplicateldException;
import excep.InvalidPasswordException;
import excep.MemberNotFoundException;
import model.User;
import util.JdbcUtil;
import excep.LoginFailException;
import model.Member;

public class MemberController extends ActionAnnotation{
    
    @Override
    public void initProcess(HttpServletRequest request,
            HttpServletResponse response)
    {   }
    
    @RequestMapping(value = "main")
    public String member_main(HttpServletRequest req, HttpServletResponse res)
            throws Exception
    {
        return "/view/main.jsp";
    }
    
    @RequestMapping(value = "join")
    public String member_joinForm(HttpServletRequest req,
            HttpServletResponse res) throws Exception
    {
        return "/view/member/joinForm.jsp";
    }
    
    @RequestMapping(value = "join", method = RequestMethod.POST)
    public String member_joinPro(HttpServletRequest req,
            HttpServletResponse response) throws Exception
    {
    	 Member newMember = new Member(req.getParameter("email"),req.getParameter("name"),
                 req.getParameter("passwd"), 
                 req.getParameter("confirmpasswd"));
    	 
    	 Map<String, Boolean> errors = new HashMap<>();
         req.setAttribute("errors", errors);
         
         newMember.vaildate(errors);
         
         if (!errors.isEmpty()) return "/view/member/joinForm.jsp";
         Connection conn = null;
         try
         {
        	 MybatisMemberDao memberDao = MybatisMemberDao.getInstance();
        Member member = memberDao.selectById(newMember.getEmail());
        if (member != null)
        {
            JdbcUtil.rollback(conn);
            throw new DuplicateldException();
        }
        memberDao.insert(newMember);
        
      
         }catch (DuplicateldException e)
         {
             errors.put("duplicateId", Boolean.TRUE);
             return "/view/member/joinForm.jsp";
         }
            return "/view/main.jsp";
            
        }
      
    @RequestMapping(value = "login")
    public String member_loginForm(HttpServletRequest req,
            HttpServletResponse res) throws Exception
    {
        return "/view/member/loginForm.jsp";
    }
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String member_loginPro(HttpServletRequest req,
            HttpServletResponse res) throws Exception
    {
        
        String email = trim(req.getParameter("email"));
        String passwd = trim(req.getParameter("passwd"));
        
        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);
        
        if (email == null || email.isEmpty()) errors.put("email", Boolean.TRUE);
        if (passwd == null || passwd.isEmpty())
            errors.put("password", Boolean.TRUE);
        
        if (!errors.isEmpty()) 
            return "/view/member/loginForm.jsp";
        
        try
        {
        	MybatisMemberDao memberDao = MybatisMemberDao.getInstance();
            
            Member member = memberDao.selectById(email);
            if (member == null) throw new LoginFailException();
            if (!member.matchPassword(passwd)) throw new LoginFailException();
            
            User user = new User(member.getEmail(), member.getName());
            req.getSession().setAttribute("authUser", user);
            
        }
        catch (LoginFailException e)
        {
            errors.put("idOrPwNotMatch", Boolean.TRUE);
            return "/view/member/loginForm.jsp";
        }
        return "/view/main.jsp";
    }
    
    private String trim(String str)
    {
        return str == null ? null : str.trim();
    }
    
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String member_logout(HttpServletRequest req, HttpServletResponse res)
            throws Exception
    {
        HttpSession session = req.getSession(false);
        if (session != null)
        {
            session.invalidate();
        }
        return "/view/main.jsp";
    }
  
}