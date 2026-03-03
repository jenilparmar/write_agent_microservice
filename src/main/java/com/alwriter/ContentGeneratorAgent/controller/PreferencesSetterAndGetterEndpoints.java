package com.alwriter.ContentGeneratorAgent.controller;

import com.alwriter.ContentGeneratorAgent.dto.SetPreferenceRequestFormat;
import com.alwriter.ContentGeneratorAgent.entity.User;
import com.alwriter.ContentGeneratorAgent.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/preferences")
public class PreferencesSetterAndGetterEndpoints {

    private UserService userService;

    PreferencesSetterAndGetterEndpoints(UserService userService){
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<String> setPreference(@RequestBody SetPreferenceRequestFormat req) {

        User user = userService.findUser(req.getEmail());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        int updateCount = userService.updateUserPreference(
                user.getId(),
                req.getPreferenceString()
        );

        if (updateCount == 1) {
            return ResponseEntity.ok("Preference updated successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Update failed");
    }

        @GetMapping
        public ResponseEntity<GetPreferenceResponse> getPreference(
                @RequestParam String email) {

            User user = userService.findUser(email);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            GetPreferenceResponse response = new GetPreferenceResponse();
            response.setPreference(user.getPreferences());

            return ResponseEntity.ok(response);
        }
}
