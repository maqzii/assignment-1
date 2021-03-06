package com.amaral.validator;

import com.amaral.GameManagementService;
import com.amaral.model.Game;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FacesValidator("nameValidator") // FacesValidator to register the validator
public class NameValidator implements Validator<String> {

    private List<Game> gameListVal = new ArrayList<>();
//    public List<Game> gameListVal = GameManagementService.gameList;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, String s) throws ValidatorException {
        if (!gameListVal.contains(s)) {
            throw new ValidatorException(new FacesMessage("Name should be one of the following: " + Arrays.toString(gameListVal.toArray())));
        }
    }
}