package fr.univamu.annuaire.web;

import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.model.PasswordResetNewPasswordBean;
import fr.univamu.annuaire.model.PasswordResetToken;
import fr.univamu.annuaire.services.account.PasswordResetService;
import fr.univamu.annuaire.validators.NewPasswordResetValidator;
import fr.univamu.annuaire.validators.PasswordResetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private PasswordResetValidator passwordResetValidator;

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private NewPasswordResetValidator newPasswordResetValidator;

    @ModelAttribute("user")
    public Person newPerson() {
        return new Person();
    }

    @ModelAttribute("newPasswordResetForm")
    public PasswordResetNewPasswordBean newPasswordResetForm() {
        return new PasswordResetNewPasswordBean();
    }

    @GetMapping("/resetPassword")
    @PreAuthorize("isAnonymous()")
    public String resetPassword() {
        return "reset_password_form";
    }

    @PostMapping("/resetPassword")
    @PreAuthorize("isAnonymous()")
    public ModelAndView resetPassword(@ModelAttribute("user") Person user, BindingResult result) {
        passwordResetValidator.validate(user, result);
        if (result.hasErrors())
            return new ModelAndView("reset_password_form");

        PasswordResetToken token = passwordResetService.generateToken(user);
        return new ModelAndView("reset_password_success", "token", URLEncoder.encode(token.getToken(), StandardCharsets.UTF_8));
    }

    @GetMapping("/resetPasswordConfirm")
    public String resetPasswordConfirm(@RequestParam("token") String token) {
        if (!passwordResetService.isTokenValid(token))
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Token is invalid or expired");

        return "reset_password_new_password_form";
    }

    @PostMapping("/resetPasswordConfirm")
    public String resetPasswordConfirm(@ModelAttribute("newPasswordResetForm") PasswordResetNewPasswordBean passwordResetNewPasswordBean, BindingResult result) throws Exception {
        newPasswordResetValidator.validate(passwordResetNewPasswordBean, result);
        if (result.hasErrors())
            return "reset_password_new_password_form";

        passwordResetService.useToken(passwordResetNewPasswordBean);

        return "reset_password_new_password_success";
    }
}
