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
import dao.MemberDao;
import excep.DuplicateldException;
import excep.InvalidPasswordException;
import excep.MemberNotFoundException;
import model.User;
import util.JdbcUtil;
import excep.LoginFailException;
import model.Member;

public class MemberController extends ActionAnnotation
{
    
    @Override
    public void initProcess(HttpServletRequest request,
            HttpServletResponse response)
    {
        // TODO Auto-generated method stub
        
    }
    
    @RequestMapping(value = "main")
    public String member_main(HttpServletRequest req, HttpServletResponse res)
            throws Exception
    {
        return "/WEB-INF/view/main.jsp";
    }
    
    @RequestMapping(value = "join")
    public String member_joinForm(HttpServletRequest req,
            HttpServletResponse res) throws Exception
    {
        return "/WEB-INF/view/member/joinForm.jsp";
    }
    
    @RequestMapping(value = "join", method = RequestMethod.POST)
    public String member_joinPro(HttpServletRequest req,
            HttpServletResponse response) throws Exception
    {
    	 Member newMember = new Member(req.getParameter("email"),req.getParameter("name"),
                 req.getParameter("passwd"), 
                 req.getParameter("confirmpasswd"));
         
        MemberDao memberDao = MemberDao.getInstance();
      
        memberDao.insert(newMember);
        
       /*Member newMember = new Member(req.getParameter("email"),req.getParameter("name"),
                req.getParameter("passwd"), 
                req.getParameter("confirmpasswd"));
        
        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);
        
        newMember.vaildate(errors);
        
        if (!errors.isEmpty()) return "/WEB-INF/view/member/joinForm.jsp";
        
        Connection conn = null;
        try
        {
            MemberDao memberDao = MemberDao.getInstance();
            Member member = memberDao.selectById(newMember.getEmail());
            if (member != null)
            {
                JdbcUtil.rollback(conn);
                throw new DuplicateldException();
            }
            
            memberDao.insert(newMember);
            */
            /*req.setAttribute("message", "占쎌돳占쎌뜚揶쏉옙占쎌뿯 占쎌끏�뙴占�");
            req.setAttribute("url", "member/main");
            return "/WEB-INF/view/alert.jsp";*/
            return "/WEB-INF/view/main.jsp";
            
        }
       /* catch (DuplicateldException e)
        {
            errors.put("duplicateId", Boolean.TRUE);
            return "/WEB-INF/view/member/joinForm.jsp";
        }
        
    }
    */
    @RequestMapping(value = "login")
    public String member_loginForm(HttpServletRequest req,
            HttpServletResponse res) throws Exception
    {
        return "/WEB-INF/view/member/loginForm.jsp";
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
            return "/WEB-INF/view/member/loginForm.jsp";
        
        try
        {
            MemberDao memberDao = MemberDao.getInstance();
            
            Member member = memberDao.selectById(email);
            if (member == null) throw new LoginFailException();
            if (!member.matchPassword(passwd)) throw new LoginFailException();
            
            User user = new User(member.getEmail(), member.getName());
            req.getSession().setAttribute("authUser", user);
            
        }
        catch (LoginFailException e)
        {
            errors.put("idOrPwNotMatch", Boolean.TRUE);
            return "/WEB-INF/view/member/loginForm.jsp";
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        req.setAttribute("message", "嚥≪뮄�젃占쎌뵥 占쎄쉐�⑨옙");
        req.setAttribute("url", "member/main");
        return "/WEB-INF/view/alert.jsp";
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
        return "/WEB-INF/view/main.jsp";
    }
    
    @RequestMapping(value = "changeinfo")
    public String member_changeinfoForm(HttpServletRequest req,
            HttpServletResponse res) throws Exception
    {
        User user = (User) req.getSession().getAttribute("authUser");
        
        try
        {
            MemberDao memberDao = MemberDao.getInstance();
            Member myInfo = memberDao.selectById(user.getEmail());
            
            req.setAttribute("myInfo", myInfo);
            return "/WEB-INF/view/member/changeInfoForm.jsp";
            
        }
        catch (MemberNotFoundException e)
        {
            req.getServletContext().log("not login", e);
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        
    }
    /* 
    @RequestMapping(value = "changeinfo", method = RequestMethod.POST)
    public String member_changeinfoPro(HttpServletRequest req,
            HttpServletResponse res) throws Exception
    {
        User user = (User) req.getSession().getAttribute("authUser");
        
        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);
        
        String curPwd = req.getParameter("curPwd");
        String newPwd = req.getParameter("newPwd");
        
        if (curPwd == null || curPwd.isEmpty())
            errors.put("curPwd", Boolean.TRUE);
        if (newPwd == null || newPwd.isEmpty())
            errors.put("newPwd", Boolean.TRUE);
        
        if (!errors.isEmpty()) return "/WEB-INF/view/member/changeInfoForm.jsp";
        
        try
        {
            MemberDao memberDao = MemberDao.getInstance();
            Member member = memberDao.selectById(user.getId());
            if (member == null) { throw new MemberNotFoundException(); }
            if (!member.matchPassword(member
                    .getPasswd())) { throw new InvalidPasswordException(); }
            member.changePassword(newPwd);
          
            memberDao.update(member);
            
            req.setAttribute("message", "占쎌돳占쎌뜚 占쎌젟癰귨옙 占쎈땾占쎌젟 占쎌끏�뙴占�");
            req.setAttribute("url", "member/myinfo");
            return "/WEB-INF/view/alert.jsp";
        }
        catch (InvalidPasswordException e)
        {
            errors.put("badCurPwd", Boolean.TRUE);
            return "/WEB-INF/view/member/changeInfoForm.jsp";
        }
        catch (MemberNotFoundException e)
        {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return "/main.jsp";
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    
*/
    @RequestMapping(value = "myinfo")
    public String member_myInfo(HttpServletRequest req, HttpServletResponse res)
            throws Exception
    {
        
        User user = (User) req.getSession().getAttribute("authUser");
        
        try
        {
            MemberDao memberDao = MemberDao.getInstance();
            Member myInfo = memberDao.selectById(user.getEmail());
            
            req.setAttribute("myInfo", myInfo);
            return "/WEB-INF/view/member/myInfo.jsp";
            
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        catch (MemberNotFoundException e)
        {
            req.getServletContext().log("not login", e);
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
            return "redirect:/member/mypage";
        }
    }
    
    @RequestMapping(value = "mypage")
    public String member_mypage(HttpServletRequest req, HttpServletResponse res)
            throws Exception
    {
        return "/WEB-INF/view/member/mypage.jsp";
    }
    
    
    // �꽴占썹뵳�딆쁽 亦낅슦釉� 疫꿸퀡�뮟 
    @RequestMapping(value = "list")
    public String member_lists(HttpServletRequest req, HttpServletResponse res)
            throws Exception
    {
        MemberDao memberDao = MemberDao.getInstance();
        List<Member> memberlist = memberDao.memberList();
        req.setAttribute("memberlist", memberlist);
        return "/WEB-INF/view/member/list.jsp";
    }
}