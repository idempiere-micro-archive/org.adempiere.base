/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 * Contributor(s): Trifon N. Trifonov				                          *
 *****************************************************************************/

package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.idempiere.common.util.CLogger;

/**
 * @author Trifon N. Trifonov
 * @author victor.perez@e-evolution.com, e-Evolution
 * <li> BF2875989 Deactivate replication records are include to replication
 * <li> https://sourceforge.net/tracker/?func=detail&aid=2875989&group_id=176962&atid=879332
 * <li> BF2947615 The document recplicacion not working
 * <li> https://sourceforge.net/tracker/?func=detail&aid=2947615&group_id=176962&atid=879332
 * <li> IDEMPIERE-218 Replication not working in ZK
 * <li> http://jira.idempiere.com/browse/IDEMPIERE-218
 */
public class MReplicationStrategy extends X_AD_ReplicationStrategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7231926756021012730L;
	public static final int REPLICATION_TABLE =0;
	public static final int REPLICATION_DOCUMENT =1;
	
	
	/**	Static Logger	*/
	@SuppressWarnings("unused")
	private static CLogger	s_log	= CLogger.getCLogger (MReplicationStrategy.class);

	
	/**
	 * Constructor
	 * @param ctx
	 * @param AD_ReplicationStrategy_ID
	 * @param trxName
	 */
	public MReplicationStrategy(Properties ctx, int AD_ReplicationStrategy_ID, String trxName) {
		super(ctx, AD_ReplicationStrategy_ID, trxName);
	}

	public MReplicationStrategy(Properties ctx, ResultSet rs, String trxName) {
		super (ctx, rs, trxName);
	}

	/**
	 * @return the list the X_AD_ReplicationTable
	 */
	public List <X_AD_ReplicationTable> getReplicationTables() {
		final String whereClause = I_AD_ReplicationTable.COLUMNNAME_AD_ReplicationStrategy_ID+"=?"; // #1
		return new Query(getCtx(), I_AD_ReplicationTable.Table_Name, whereClause, get_TrxName())
			.setParameters(getAD_ReplicationStrategy_ID())
			.setOnlyActiveRecords(true)
			.setApplyAccessFilter(false)
			.list()
		;
	}
	
	/**
	 * @return the list the X_AD_ReplicationDocument
	 */
	public List<X_AD_ReplicationDocument> getReplicationDocuments() {
	    	final String whereClause = I_AD_ReplicationDocument.COLUMNNAME_AD_ReplicationStrategy_ID+"=?"; // #1
		return new Query(getCtx(),I_AD_ReplicationDocument.Table_Name,whereClause,get_TrxName())
			.setParameters(getAD_ReplicationStrategy_ID())
			.setOnlyActiveRecords(true)
			.setApplyAccessFilter(false)
			.list()
		;	
	}
	
	/**
	 * 
	 * @param AD_Table_ID
	 * @return X_AD_ReplicationTable table to replication
	 */
	public static X_AD_ReplicationTable getReplicationTable(Properties ctx ,int AD_ReplicationStrategy_ID, int AD_Table_ID)
	{
	    	final String whereClause = I_AD_ReplicationTable.COLUMNNAME_AD_ReplicationStrategy_ID + "=? AND "
	    				 			 + I_AD_ReplicationTable.COLUMNNAME_AD_Table_ID + "=?";
		return new Query(ctx, I_AD_ReplicationTable.Table_Name, whereClause, null)
			.setOnlyActiveRecords(true)
			.setApplyAccessFilter(false)
			.setParameters(AD_ReplicationStrategy_ID, AD_Table_ID)
			.first()
		;	
	}
	
	/**
	 * 
	 * @param AD_Table_ID
	 * @return X_AD_ReplicationDocument Document to replication
	 */
	public static X_AD_ReplicationDocument getReplicationDocument(Properties ctx ,int AD_ReplicationStrategy_ID , int AD_Table_ID)
	{
	    	final String whereClause = I_AD_ReplicationDocument.COLUMNNAME_AD_ReplicationStrategy_ID + "=? AND "
			 		 				 + I_AD_ReplicationDocument.COLUMNNAME_AD_Table_ID + "=?";
		return new Query(ctx, I_AD_ReplicationDocument.Table_Name, whereClause, null)
			.setOnlyActiveRecords(true)
			.setApplyAccessFilter(false)
			.setParameters(AD_ReplicationStrategy_ID, AD_Table_ID)
			.first()
		;
	}
	
	/**
	 * 
	 * @param AD_Table_ID
	 * @return X_AD_ReplicationDocument Document to replication
	 */
	public static X_AD_ReplicationDocument getReplicationDocument(Properties ctx ,int AD_ReplicationStrategy_ID , int AD_Table_ID, int C_DocType_ID)
	{
	    final String whereClause = I_AD_ReplicationDocument.COLUMNNAME_AD_ReplicationStrategy_ID + "=? AND "
	    						 + I_AD_ReplicationDocument.COLUMNNAME_AD_Table_ID + "=? AND "
	    						 + I_AD_ReplicationDocument.COLUMNNAME_C_DocType_ID + "=?";
		return new Query(ctx, X_AD_ReplicationDocument.Table_Name, whereClause, null)
			.setOnlyActiveRecords(true)
			.setApplyAccessFilter(false)
			.setParameters(AD_ReplicationStrategy_ID, AD_Table_ID, C_DocType_ID)
			.first();
	}

}
