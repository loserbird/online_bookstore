package com.loserbird.bookstore.controller.portal;

import com.loserbird.bookstore.pojo.User;
import com.loserbird.bookstore.service.IUserService;
import com.loserbird.bookstore.util.MD5Util;
import com.loserbird.bookstore.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

/**
 * @Author: bingqin
 * @Date: 2018/1/7
 * @Description:
 **/
@Controller
@RequestMapping("/user/")
public class UserController {


    @Autowired
    private IUserService iUserService;


    //登录
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ModelAndView login(String username, String password, String loginCheckcode,int role,HttpSession session) {
        ModelAndView mav = new ModelAndView();
        String checkcode = (String) session.getAttribute("checkcode");
        String loginMsg = "";
        if(!loginCheckcode.equalsIgnoreCase(checkcode)){
            loginMsg = "验证码不正确";
            mav.setViewName("/login.jsp");
            mav.addObject("loginMsg",loginMsg);
            return mav;
        }
        User user = iUserService.login(username, MD5Util.MD5EncodeUtf8(password));
        if(user == null || user.getRole() != role ){
            loginMsg = "此账号不存在或密码错误";
            mav.setViewName("/login.jsp");
            mav.addObject("loginMsg",loginMsg);
            return mav;
        }
        session.setAttribute("user",user);
        mav.setViewName("redirect:/middle.jsp");
        return mav;

    }

    //退出
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("user");
        return new ModelAndView("/login.jsp");
    }

    //根据账号名判断用户是否存在
    @RequestMapping(value = "isUserExist.do")
    public void isUserExist(String username,HttpServletResponse resp) throws IOException {
        User user = iUserService.findUserByUsername(username);
        if(user!=null){
            resp.getWriter().print("0");
        }else{
            resp.getWriter().print("1");
        }
    }
    //邮箱是否已经被注册
    @RequestMapping(value = "isEmailExist.do")
    public void isEmailExist(String email,HttpServletResponse resp)throws IOException {
        User user = iUserService.findUserByEmail(email);
        if(user!=null){
            resp.getWriter().print("0");
        }else{
            resp.getWriter().print("1");
        }
    }

    //注册操作
    @RequestMapping(value = "regist.do")
    public ModelAndView regist(User user,String rpassword){
        ModelAndView mav = new ModelAndView();
        if(!user.getPassword().equals(rpassword)){
            mav.addObject("registError", "两次密码不一致");
            mav.setViewName("/regist.jsp");
        }
        user.setRole(0);
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        iUserService.addUser(user);
        mav.setViewName("redirect:/registSuccess.jsp");
        return mav;
    }

    //给用户邮箱发送验证码
    @RequestMapping(value = "getCheckCode.do")
    public void getCheckCode(String username,HttpSession session){
        User user = iUserService.findUserByUsername(username);
        if(user != null){
            String email = user.getEmail();
            String checkCode = UUID.randomUUID().toString().substring(0, 6);
            String emailMsg = "您的验证码是"+checkCode;
            session.setAttribute("activecode", checkCode);
            MailUtil.sendMail(email, emailMsg);
        }
    }

    //找回密码
    @RequestMapping(value = "findPassword.do")
    public ModelAndView findPassword(String password,String rpassword,String checkcode,String username,HttpSession session){
        ModelAndView mav = new ModelAndView();
        String activecode = (String) session.getAttribute("activecode");
        if(!checkcode.equals(activecode)){
            mav.addObject("loginMsg","修改密码错误，验证码不正确");
            mav.setViewName("/login.jsp");
            return mav;
        }
        iUserService.updatePassword(username,MD5Util.MD5EncodeUtf8(password));
        mav.addObject("loginMsg","修改密码成功");
        mav.setViewName("/login.jsp");
        return mav;

    }

    //修改个人信息
    @RequestMapping(value = "update.do")
    public ModelAndView update(HttpSession session,String email,String username){
        ModelAndView mav = new ModelAndView();
        User user = (User) session.getAttribute("user");
        user.setEmail(email);
        user.setUsername(username);
        iUserService.update(user);
        session.setAttribute("user",user);
        mav.addObject("updateResult","1");
        mav.setViewName("/userInfo.jsp");
        return mav;
    }

    //修改密码
    @RequestMapping(value = "updatePassword.do")
    public ModelAndView updatePassword(String oldPassword,String newPassword,HttpSession session){
        User user = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        if(!user.getPassword().equals(MD5Util.MD5EncodeUtf8(oldPassword))){
            mav.addObject("updatePasswordError", "原密码错误");
            mav.setViewName("/updatePassword.jsp");
            return mav;
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(newPassword));
        iUserService.updatePassword(user.getUsername(),user.getPassword());
        session.setAttribute("user",user);
        mav.addObject("updatePasswordError", "修改成功");
        mav.setViewName("/updatePassword.jsp");
        return mav;
    }


    //验证码
    @RequestMapping(value = "checkcode.do")
    public void checkcode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int width = 60;
        int height = 20;
        // 绘制一张内存中图片
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //得到画笔对象
        Graphics g =bi.getGraphics();
        Color c= getRandomColor(200,255);
        //设置画笔颜色
        g.setColor(c);
        g.fillRect(0,0,width,height);		//绘制图片背景

        g.setColor(Color.WHITE);
        g.drawRect(0, 0, width - 1, height - 1);//绘制边框


        char[] ch="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        Random r= new Random();
        int len = ch.length,index;
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<4;i++){
            index = r.nextInt(len);
            g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));
            g.drawString(ch[index]+"",(i*15)+3,18);
            sb.append(ch[index]);
        }
        req.getSession().setAttribute("checkcode",sb.toString());
        ImageIO.write(bi, "JPG", resp.getOutputStream());
    }

    private Color getRandomColor(int fc, int bc){
        Random random = new Random();
        if(fc>255){
            fc = 255;
        }
        if(bc>255){
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
