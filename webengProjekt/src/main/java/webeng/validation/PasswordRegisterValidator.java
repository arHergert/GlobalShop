package webeng.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("webeng.validation.PasswordRegisterValidator")
public class PasswordRegisterValidator implements Validator {

	/**
	 * Ein Pattern, das die Syntax des Passworts vorgibt.
	 * <p>
	 * <b>^</b>                 Anfang des Strings<br>
	 * <b>(?=.*[0-9])</b>       Eine Ziffer muss mind. einmal vorkommen<br>
	 * <b>(?=.*[a-zA-Z])</b>    Ein Groﬂ- oder Kleinbuchstabe von A-Z muss mind. einmal vorkommen<br>
	 * <b>.{6,}</b>             Zeichenfolge egal, muss jedoch 6 Zeichen lang sein<br>
	 * <b>$</b> 				Ende des Strings<br>
	 * </p>
	 */
	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$";
	private Pattern pattern;
	private Matcher matcher;
	
	public PasswordRegisterValidator() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		matcher = pattern.matcher(value.toString());
		
		if(!matcher.matches()) {
			
			FacesMessage msg = new FacesMessage("Password validation failed.", "Invalid password format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
			
		}
		
	}

}
