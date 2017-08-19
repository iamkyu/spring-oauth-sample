package io.iamkyu.controller;

import io.iamkyu.exception.DuplicateEmailException;
import io.iamkyu.model.FrontUserDetail;
import io.iamkyu.model.User;
import io.iamkyu.model.UserCreateRequest;
import io.iamkyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Kj Nam
 */
@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired private ProviderSignInUtils providerSignInUtils;

    @Autowired UserService userService;

    @GetMapping
    public String redirectRequestToRegistrationPage(WebRequest request, ModelMap modelMap) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        UserProfile userProfile = connection.fetchUserProfile();
        UserCreateRequest userCreateRequest = UserCreateRequest.create(userProfile);

        modelMap.put("user", userCreateRequest);
        return "signup";
    }

    @PostMapping
    public String resistUser(@ModelAttribute UserCreateRequest userCreateRequest, WebRequest request) {
        User user = null;

        try {
            user = userService.create(userCreateRequest);
        } catch (DuplicateEmailException e) {
            return String.format("redirect:/error?message=%s", e.getMessage());
        }

        providerSignInUtils.doPostSignUp(user.getEmail(), request);

        FrontUserDetail frontUserDetail = new FrontUserDetail(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                frontUserDetail, null, frontUserDetail.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }

}
