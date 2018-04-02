/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.idempiere.org/license.html           *
 *****************************************************************************/
package org.compiere.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.I_M_InOut;
import org.compiere.model.I_M_InOutLine;
import org.compiere.model.I_M_InOutLineMA;
import org.compiere.orm.MTable;
import org.compiere.orm.Query;
import org.idempiere.common.exceptions.AdempiereException;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.DB;
import org.idempiere.common.util.Env;
import org.idempiere.common.util.Util;

/**
 *	Shipment Material Allocation
 *	
 *  @author Jorg Janke
 *  @version $Id: MInOutLineMA.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 */
public class MInOutLineMA extends X_M_InOutLineMA
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2610075295914545809L;

	/** Log								*/
	private static CLogger		s_log = CLogger.getCLogger (MInOutLineMA.class);

	/**
	 * 	Get Material Allocations for Line
	 *	@param ctx context
	 *	@param M_InOutLine_ID line
	 *	@param trxName trx
	 *	@return allocations
	 */
	public static MInOutLineMA[] get (Properties ctx, int M_InOutLine_ID, String trxName)
	{
		Query query = MTable.get(ctx, MInOutLineMA.Table_Name)
							.createQuery(I_M_InOutLineMA.COLUMNNAME_M_InOutLine_ID+"=?", trxName);
		query.setParameters(M_InOutLine_ID);
		List<MInOutLineMA> list = query.list();
		MInOutLineMA[] retValue = new MInOutLineMA[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	get
	
	/**
	 * Delete all Material Allocation for InOut
	 * @param M_InOut_ID shipment
	 * @param trxName transaction
	 * @return number of rows deleted or -1 for error
	 */
	public static int deleteInOutMA (int M_InOut_ID, String trxName)
	{
		StringBuilder sql = new StringBuilder("DELETE FROM M_InOutLineMA ma WHERE EXISTS ")
			.append("(SELECT * FROM M_InOutLine l WHERE l.M_InOutLine_ID=ma.M_InOutLine_ID")
			.append(" AND M_InOut_ID=").append(M_InOut_ID).append(")");
		return DB.executeUpdate(sql.toString(), trxName);
	}	//	deleteInOutMA
	
	/**
	 * 	Delete all Material Allocation for InOutLine
	 *	@param M_InOutLine_ID Shipment Line
	 *	@param trxName transaction
	 *	@return number of rows deleted or -1 for error
	 */
	public static int deleteInOutLineMA (int M_InOutLine_ID, String trxName)
	{
		String sql = "DELETE FROM M_InOutLineMA ma WHERE ma.M_InOutLine_ID=? AND ma.IsAutoGenerated='Y'";
		return DB.executeUpdate(sql, M_InOutLine_ID, trxName);
	}	//	deleteInOutLineMA
		
	
//	/**	Logger	*/
//	private static CLogger	s_log	= CLogger.getCLogger (MInOutLineMA.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_InOutLineMA_ID ignored
	 *	@param trxName trx
	 */
	public MInOutLineMA (Properties ctx, int M_InOutLineMA_ID, String trxName)
	{
		super (ctx, M_InOutLineMA_ID, trxName);
		if (M_InOutLineMA_ID != 0)
			throw new IllegalArgumentException("Multi-Key");
	}	//	MInOutLineMA

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MInOutLineMA (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MInOutLineMA
	
	/**
	 * 	Parent Constructor
	 *	@param parent parent
	 *	@param M_AttributeSetInstance_ID asi
	 *	@param MovementQty qty
	 *  @param DateMaterialPolicy
	 */
	public MInOutLineMA (MInOutLine parent, int M_AttributeSetInstance_ID, BigDecimal MovementQty,Timestamp DateMaterialPolicy)
	{
		this (parent,M_AttributeSetInstance_ID,MovementQty,DateMaterialPolicy,true);
	}
	
	/**
	 * @param parent
	 * @param M_AttributeSetInstance_ID
	 * @param MovementQty
	 * @param DateMaterialPolicy
	 * @param isAutoGenerated
	 */
	public MInOutLineMA (MInOutLine parent, int M_AttributeSetInstance_ID, BigDecimal MovementQty,Timestamp DateMaterialPolicy,boolean isAutoGenerated)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setM_InOutLine_ID(parent.getM_InOutLine_ID());
		//
		setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
		setMovementQty(MovementQty);
		if (DateMaterialPolicy == null)
		{
			if (M_AttributeSetInstance_ID > 0)
			{
				DateMaterialPolicy  = MStorageOnHand.getDateMaterialPolicy(parent.getM_Product_ID(), M_AttributeSetInstance_ID, parent.get_TrxName());
			}
			if (DateMaterialPolicy == null)
				DateMaterialPolicy = parent.getParent().getMovementDate();
		}
		setDateMaterialPolicy(DateMaterialPolicy);
		setIsAutoGenerated(isAutoGenerated);
	}	//	MInOutLineMA

	@Override
	public void setDateMaterialPolicy(Timestamp DateMaterialPolicy) {
		if (DateMaterialPolicy != null)
			DateMaterialPolicy = Util.removeTime(DateMaterialPolicy);
		super.setDateMaterialPolicy(DateMaterialPolicy);
	}
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuilder sb = new StringBuilder ("MInOutLineMA[");
		sb.append("M_InOutLine_ID=").append(getM_InOutLine_ID())
			.append(",M_AttributeSetInstance_ID=").append(getM_AttributeSetInstance_ID())
			.append(", Qty=").append(getMovementQty())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	public static MInOutLineMA addOrCreate(MInOutLine line, int M_AttributeSetInstance_ID, BigDecimal MovementQty, Timestamp DateMaterialPolicy){
		return addOrCreate(line,M_AttributeSetInstance_ID,MovementQty,DateMaterialPolicy,true);
	}
	
	public static MInOutLineMA addOrCreate(MInOutLine line, int M_AttributeSetInstance_ID, BigDecimal MovementQty, Timestamp DateMaterialPolicy,boolean isAutoGenerated)
	{
		Query query = new Query(Env.getCtx(), I_M_InOutLineMA.Table_Name, "M_InOutLine_ID=? AND M_AttributeSetInstance_ID=? AND DateMaterialPolicy=trunc(cast(? as date))", 
					line.get_TrxName());
		MInOutLineMA po = query.setParameters(line.getM_InOutLine_ID(), M_AttributeSetInstance_ID, DateMaterialPolicy).first();
		if (po == null)
			po = new MInOutLineMA(line, M_AttributeSetInstance_ID, MovementQty, DateMaterialPolicy,isAutoGenerated);
		else
			po.setMovementQty(po.getMovementQty().add(MovementQty));
		return po;
	}
	
	/**
	 * Total qty on LineMA for M_InoutLine
	 * @param M_InOutLine_ID
	 * @param trxName
	 * @return
	 */
	public static BigDecimal getManualQty (int M_InOutLine_ID, String trxName)
	{
		String sql = "SELECT SUM(movementqty) FROM M_InOutLineMA ma WHERE ma.M_InOutLine_ID=? AND ma.IsAutoGenerated='N'";
		BigDecimal totalQty = DB.getSQLValueBD(trxName, sql, M_InOutLine_ID);
		return totalQty==null?Env.ZERO:totalQty;
	} //totalLineQty
	
	/**
	 * 	Get Material Allocations from shipment which is not returned
	 *	@param ctx context
	 *	@param M_InOutLine_ID line
	 *	@param trxName trx
	 *	@return allocations
	 */
	public static MInOutLineMA[] getNonReturned (Properties ctx, int M_InOutLine_ID, String trxName)
	{
		String sql = "SELECT * FROM M_InoutLineMA_Returned WHERE (returnedQty<>movementQty or returnedQty is null) and m_inoutline_id=? ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MInOutLineMA> list = new ArrayList<MInOutLineMA>();
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, M_InOutLine_ID);
			
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MInOutLineMA lineMA = new MInOutLineMA(ctx, rs, trxName);
				list.add(lineMA);
			}
			
		}catch (SQLException ex)
		{
			s_log.log(Level.SEVERE, sql, ex);
			throw new AdempiereException(ex.getLocalizedMessage(),ex);
		}finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		MInOutLineMA[] retValue = new MInOutLineMA[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getNonReturned
	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//Set DateMaterialPolicy
		if(!newRecord && is_ValueChanged(I_M_InOutLineMA.COLUMNNAME_M_AttributeSetInstance_ID)){
			//TODO Require testing for all scenario
			I_M_InOutLine line = getM_InOutLine();
			
			Timestamp dateMPolicy = null;
			if(getM_AttributeSetInstance_ID()>0)
			{
				dateMPolicy = MStorageOnHand.getDateMaterialPolicy(line.getM_Product_ID(), getM_AttributeSetInstance_ID(), get_TrxName());
			}
			
			if(dateMPolicy == null)
			{
				I_M_InOut inout = line.getM_InOut();
				dateMPolicy = inout.getMovementDate();
			}
			
			setDateMaterialPolicy(dateMPolicy);
		}
		
		return true;
	} //beforeSave

}	//	MInOutLineMA

