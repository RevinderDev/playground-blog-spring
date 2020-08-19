package com.revinder.playgroundblog.util.modelassemblers;

import com.revinder.playgroundblog.controller.UserController;
import com.revinder.playgroundblog.model.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User entity) {
        /* TODO: Make dto of user to get rid of password field. */
        entity.setPassword(null);
        return EntityModel.of(entity,
                linkTo(methodOn(UserController.class).findById(entity.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).findAll()).withRel("users"));
    }
}
