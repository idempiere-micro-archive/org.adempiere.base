package org.compiere.model;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.idempiere.common.util.CCache;
import org.idempiere.common.util.DB;

/**	Convention for the first year of depreciation (ex. FMCON, FYCON ...)
 *	@author Teo Sarca, SC Arhipac SRL
 *	@version $Id$
 */
public class MDepreciationConvention extends X_A_Depreciation_Convention
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2274629486216430723L;

	/**
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param A_Depreciation_Convention_ID id
	 *	@param trxName transaction name
	 */
	public MDepreciationConvention(Properties ctx, int A_Depreciation_Convention_ID, String trxName)
	{
		super (ctx, A_Depreciation_Convention_ID, trxName);
		//~ if (A_Depreciation_Convention_ID == 0)
		//~ {
		//~ }
	}	//	MDepreciationConvention

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MDepreciationConvention (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MDepreciationConvention

	/**		Cache									*/
	private static CCache<Integer,MDepreciationConvention> s_cache = new CCache<Integer,MDepreciationConvention>(Table_Name, 5);
	//~ /**		Static logger							*/
	//~ private static Logger s_log = CLogger.getCLogger(MDepreciationConvention.class);
	
	public static MDepreciationConvention get(Properties ctx, int A_Depreciation_Convention_ID) {
		Integer key = Integer.valueOf(A_Depreciation_Convention_ID);
		MDepreciationConvention conv = s_cache.get(key);
		if (conv != null) {
			return conv;
		}
		conv = new MDepreciationConvention(ctx, A_Depreciation_Convention_ID, null);
		if (conv.get_ID() > 0) {
			s_cache.put(key, conv);
		} else {
			conv = null;
		}
		return conv;
	} // get

	/**	*/
	public BigDecimal invoke (MDepreciationWorkfile assetwk, MAssetAcct assetAcct, int Flag, int Period) {
		return invoke(assetwk.getA_Asset_ID(), assetAcct.getPostingType(), assetAcct.get_ID(), Flag, Period);
	}
	
	/**	*/
	public BigDecimal invoke (int A_Asset_ID, String PostingType, int A_Asset_Acct_ID, int Flag, int Period) {
		String conventionType = getConventionType();
		BigDecimal retValue = null;
	
		if (log.isLoggable(Level.FINE)) log.fine("Entering: ConventionType=" + conventionType	
						+ "A_Asset_ID=" + A_Asset_ID + ", PostingType=" + PostingType + ", A_Asset_Acct_ID=" + A_Asset_Acct_ID
						+ ", Flag=" + Flag + ", Period=" + Period
			);
		
		if (conventionType.equalsIgnoreCase("FMCON")) {
			return apply_FMCON(A_Asset_ID, PostingType, A_Asset_Acct_ID, Flag, Period);
		}
		else {
			String sql = "{ ? = call "+ conventionType + "(?, ?, ?, ?, ?) }";
			CallableStatement cs = null;
			try {
				cs = DB.prepareCall(sql);
				cs.registerOutParameter(1, java.sql.Types.DECIMAL);
				cs.setInt(2, A_Asset_ID);
				cs.setString(3, PostingType);
				cs.setInt(4, A_Asset_Acct_ID);
				cs.setInt(5, Flag);
				cs.setInt(6, Period);
				cs.execute();						
				retValue = cs.getBigDecimal(1);
			} catch (Exception e) {
				log.log(Level.SEVERE, sql, e);
			}
			finally {
				DB.close(cs);
				cs = null;
			}
		}
		//
		if (retValue == null) {
			retValue = BigDecimal.ZERO;
		}
		//
		if (log.isLoggable(Level.FINE)) log.fine("Leaving: retValue=" + retValue);
		return retValue;
	}

	public BigDecimal apply_FMCON(int A_Asset_ID, String PostingType, int A_Asset_Acct_ID, int Flag, int Period) {
		return BigDecimal.ONE;
	}
}
