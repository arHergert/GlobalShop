package webeng.data;

public abstract class DAOFactory {
	// List of DAO types supported by the factory
	public enum Backend
	{
		H2
	}
		
	// There will be a method for each DAO that can be
	// created. The concrete factories will have to
	// implement these methods.
	public abstract ProductDAO getProductDAO();
	public abstract UserDAO getUserDAO();
	public abstract TransactionDAO getTransactionDAO();

	public static DAOFactory getDAOFactory(Backend whichFactory) {
		switch (whichFactory) {
		case H2:{
				return new H2DAOFactory();
				}
		default:
			return null;
		}
	}
	
	
	
	
	
}//end class DAOFactory