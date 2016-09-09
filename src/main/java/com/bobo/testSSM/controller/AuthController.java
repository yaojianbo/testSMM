package com.bobo.testSSM.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.bobo.testSSM.dao.pojo.User;
import com.bobo.testSSM.service.IAuthService;
import com.bobo.testSSM.util.VerifyCodeUtil;

/**
 * AuthController:用户登录/登出(shiro权限管理)
 * </p>
 * 采用ModelAndView模式的controller
 * 
 * @author YAOJIANBO
 *
 */
@Controller
@RequestMapping("/auth")
public class AuthController
{
	private static Logger Logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private IAuthService authService;

	/**
	 * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
	 * @throws IOException 
	 */
	@RequestMapping("/getVerifyCodeImage")
	public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// 设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		
		// 将验证码放到HttpSession里面
		request.getSession().setAttribute("verifyCode", verifyCode);
		
		Logger.debug("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
		
		// 设置输出的内容的类型为JPEG图像
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE,
				Color.BLACK, null);
		
		// 写给浏览器
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
	}

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//获取HttpSession中的验证码  
        String verifyCode = (String)request.getSession().getAttribute("verifyCode");
        //获取用户请求表单中输入的验证码  
        String submitCode = WebUtils.getCleanParam(request, "verifyCode");

		Logger.info(username + "," + password + " login......");
		
		// System.out.println("用户[" + username + "]登录时输入的验证码为[" + submitCode + "],HttpSession中的验证码为[" + verifyCode + "]");  
        if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(verifyCode, submitCode.toLowerCase()))
        {  
        	mav.setViewName("login");
			mav.addObject("msg", "验证码错误!");
			return mav;
        }

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
		{
			mav.setViewName("login");
			mav.addObject("msg", "用户名/密码 不能为空!");
			return mav;
		}

		User user = authService.getUserByUserName(username);
		if (null == user)
		{
			mav.setViewName("login");
			mav.addObject("msg", "用户不存在!");
			return mav;
		}

		if (!user.getPassword().equals(password))
		{
			mav.setViewName("login");
			mav.addObject("msg", "账号密码错误!");
			return mav;
		}

		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		// System.out.println("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		
		Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        
        //验证是否登录成功  
        if(currentUser.isAuthenticated())
        {
            Logger.debug("用户[" + username + "]通过身份验证登录通过");
        }
        else
        {
            token.clear();
            
            mav.setViewName("login");
			mav.addObject("msg", "验证未通过!");
			return mav;
        }

		// mav.setViewName("index");
        mav.setViewName(InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/jsp/index.jsp");

		return mav;
	}

	/** 
     * 用户登出 
     */  
    @RequestMapping("/logout")  
    public String logout(HttpServletRequest request)
    {  
         SecurityUtils.getSubject().logout();  
         return "login";  
    }
}
