package com.fruitsalesplatform.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fruitsalesplatform.entity.User;
import com.fruitsalesplatform.service.UserService;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;

@Controller
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/setting.action")
	public String setting() {
		return "/setting.jsp";
	}
	
	@RequestMapping(value = "/user/updateOther.action",method = RequestMethod.POST)
	public String updateUser(User user,Model model,HttpServletRequest request) {
		String userId = ((User)request.getSession().getAttribute("user")).getUserId();
		user.setUserId(userId);
		userService.update(user);
		model.addAttribute("updateMessage","修改成功！");
		//重新获取User加入Session
		User newUser = userService.get(userId);
		request.getSession().setAttribute("user", newUser);
		return "/setting.jsp";
	}
	
	@RequestMapping(value = "/user/updatePassword.action",method = RequestMethod.POST)
	public String updatePassword(String oldPassword,String newPassword,String confirmPassword,Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user.getPassword().equals(oldPassword)) {
			user.setPassword(newPassword);
			userService.update(user);
			model.addAttribute("updateMessage","修改成功！");
		}
		else 
			model.addAttribute("updateMessage","修改失败！原密码错误！");
		return "/setting.jsp";
	}
	
	@RequestMapping(value = "/user/bindUser.action",method = RequestMethod.POST)
	public void bindUser(String userName,String password,HttpServletRequest request,HttpServletResponse response) {
		User user = userService.getUserFromUserName(userName);
		if(user == null) {
			request.getSession().setAttribute("errorMsg", "绑定账号到QQ失败，用户名错误！");
			try {
				response.sendRedirect(request.getContextPath() + "/user/toLogin.action");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(!user.getPassword().equals(password)) {
			request.getSession().setAttribute("errorMsg", "绑定账号到QQ失败，密码错误！");
			try {
				response.sendRedirect(request.getContextPath() + "/user/toLogin.action");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			user.setQQOpenId((String) request.getSession().getAttribute("openId"));
			userService.update(user);
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("bindMessage", "绑定成功！");
			try {
				response.sendRedirect(request.getContextPath() + "/toHome.action");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("/user/qqLogin.action")
	public void toQQLogin(HttpServletRequest request,HttpServletResponse response) {
		 try {
			response.sendRedirect(new Oauth().getAuthorizeURL(request));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QQConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/user/toBindUser.action")
	public String toBindUser() {
		return "/bindUser.jsp";
	}
	
	@RequestMapping("/toHome.action")
	public String toHome(HttpServletRequest request,Model model) {
		model.addAttribute("bindMessage", request.getSession().getAttribute("bindMessage"));
		request.getSession().removeAttribute("bindMessage");
		return "/home.jsp";
	}
	
	@RequestMapping("/user/judgeBind.action")
	public void judgeBind(HttpServletRequest request,HttpServletResponse response) {
		try {
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
			String accessToken = null,openId = null;
			@SuppressWarnings("unused")
			long tokenExpireIn = 0L;
			if(accessTokenObj.getAccessToken().equals(""))
				System.out.println("没有获取到相应参数");
			else {
				accessToken = accessTokenObj.getAccessToken();
				tokenExpireIn = accessTokenObj.getExpireIn();
				
				//request.getSession().setAttribute("accessory_token", accessToken);
				//request.getSession().setAttribute("token_expirein", tokenExpireIn);
				
				OpenID openIDObj = new OpenID(accessToken);
				openId = openIDObj.getUserOpenID();
				
				request.getSession().setAttribute("openId", openId);
				
				if(userService.getUserFromQQOpenId(openId) == null)
					response.sendRedirect(request.getContextPath() + "/user/toBindUser.action");
				else {
					User user = userService.getUserFromQQOpenId(openId);
					request.getSession().setAttribute("user", user);
					response.sendRedirect(request.getContextPath() + "/toHome.action");
				}
			}
		} catch (QQConnectException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/user/logout.action")
	public void logout(HttpServletRequest request,HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("openId");
		try {
			response.sendRedirect(request.getContextPath() + "/user/toLogin.action");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/user/toLogin.action")
	public String toLogin(HttpServletRequest request,Model model) {
		model.addAttribute("errorMsg", request.getSession().getAttribute("errorMsg"));
		request.getSession().removeAttribute("errorMsg");
		return "/login.jsp";
	}

	@RequestMapping("/user/login.action")
	public void login(User user, HttpServletRequest request,HttpServletResponse response) {
		Map<Object, Object> map = new HashMap<>();
		map.put("username", user.getUserName());
		map.put("password", user.getPassword());
		List<User> userList = userService.find(map);
		if (userList != null && userList.size() > 0) {
			request.getSession().setAttribute("user", userList.get(0));
			try {
				response.sendRedirect(request.getContextPath() + "/toHome.action");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		request.getSession().setAttribute("errorMsg", "登录失败！账号或密码错误");
		try {
			response.sendRedirect(request.getContextPath() + "/user/toLogin.action");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/user/registerPage.action")
	public String toRegister() {
		return "/register.jsp";
	}

	@RequestMapping("/user/register.action")
	public String register(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<>();
		map.put("username", user.getUserName());
		List<User> userList = userService.find(map);
		if(userList != null && userList.size() > 0) {
			model.addAttribute("errorMsg", "注册失败，用户名已被占用！");
			return "/register.jsp";
		}
		user.setUserId(UUID.randomUUID().toString());
		userService.insert(user);
		model.addAttribute("noticeMsg","注册成功！请输入账号密码登录");
		return "/login.jsp";
	}
}
