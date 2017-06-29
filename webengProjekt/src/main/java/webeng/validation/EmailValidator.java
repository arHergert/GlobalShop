package webeng.validation;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.application.FacesMessage;

@FacesValidator("webeng.validation.EmailValidator")
public class EmailValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		boolean error = true;
		if(value instanceof String) {
			error = false;
			String s = (String)value;
			if(!(s.indexOf('@')==1)) {
				error = true;
			}
		}
		if(error) {
			throw new ValidatorException(new FacesMessage("Keine EMail-Adresse", "Der angegebene String ist keine EMail-Adresse"));
		}
	}

}
