package io.tastypenguinbacon.shoppingcart.rest;

import io.tastypenguinbacon.shoppingcart.rest.dto.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Created by pingwin on 04.01.18.
 */
@RestController
@RequestMapping(
        value = "users",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class Users {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<UserDTO> getUsers() {
        return Collections.emptyList();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public UserDTO createUser() {
        return null;
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public UserDTO getUserInformation(
            @PathVariable("userId") String userId) {
        return null;
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public void deleteUser(
            @PathVariable("userId") String userId) {
    }
}
