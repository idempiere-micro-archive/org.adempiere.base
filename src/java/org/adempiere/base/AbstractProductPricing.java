/******************************************************************************
 * Copyright (C) 2017 Diego Ruiz                                              *
 * Copyright (C) 2017 Bx Service GmbH                                         *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.base;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.I_C_InvoiceLine;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.I_C_ProjectLine;
import org.compiere.model.I_M_RMALine;
import org.compiere.model.I_M_RequisitionLine;
import org.idempiere.common.util.Env;

public abstract class AbstractProductPricing implements IProductPricing {
	
	protected int 		  m_M_Product_ID;
	protected int 		  m_C_BPartner_ID;
	protected BigDecimal  m_Qty = Env.ONE;
	protected boolean	  m_isSOTrx = true;
	protected String      trxName   = null;
	
	protected int   	  m_M_PriceList_Version_ID = 0;
	protected int		  m_M_PriceList_ID = 0;
	protected Timestamp   m_PriceDate;	

	public AbstractProductPricing() {}
	
	@Override
	public void setInitialValues(int M_Product_ID, int C_BPartner_ID, BigDecimal qty, boolean isSOTrx, String trxName) {
		this.trxName=trxName;
		m_M_Product_ID = M_Product_ID;
		m_C_BPartner_ID = C_BPartner_ID;
		
		if (qty != null && Env.ZERO.compareTo(qty) != 0)
			m_Qty = qty;
		m_isSOTrx = isSOTrx;
	}

	@Override
	public int getM_Product_ID() {
		return m_M_Product_ID;
	}

	@Override
	public int getM_PriceList_ID() {
		return m_M_PriceList_ID;
	}

	@Override
	public void setM_PriceList_ID(int M_PriceList_ID) {
		m_M_PriceList_ID = M_PriceList_ID;
	}

	@Override
	public void setM_PriceList_Version_ID(int M_PriceList_Version_ID) {
		m_M_PriceList_Version_ID = M_PriceList_Version_ID;
	}
	
	@Override
	public void setQty(BigDecimal qty) {
		m_Qty = qty;
	}

	@Override
	public Timestamp getPriceDate() {
		return m_PriceDate;
	}

	@Override
	public void setPriceDate(Timestamp priceDate) {
		m_PriceDate = priceDate;
	}
	
	@Override
	public void setOrderLine(I_C_OrderLine orderLine, String trxName) {
		m_M_Product_ID = orderLine.getM_Product_ID();
		m_C_BPartner_ID = orderLine.getC_BPartner_ID();
		BigDecimal qty = orderLine.getQtyOrdered();
		if (qty != null && Env.ZERO.compareTo(qty) != 0)
			m_Qty = qty;
		m_isSOTrx = orderLine.getC_Order().isSOTrx();
		m_PriceDate = orderLine.getDateOrdered();
		this.trxName = trxName;
	}
	
	@Override
	public void setInvoiceLine(I_C_InvoiceLine invoiceLine, String trxName) {
		m_M_Product_ID = invoiceLine.getM_Product_ID();
		m_C_BPartner_ID = invoiceLine.getC_Invoice().getC_BPartner_ID();
		BigDecimal qty = invoiceLine.getQtyInvoiced() != null ? 
				invoiceLine.getQtyInvoiced() : invoiceLine.getQtyEntered();
		if (qty != null && Env.ZERO.compareTo(qty) != 0)
			m_Qty = qty;
		m_isSOTrx = invoiceLine.getC_Invoice().isSOTrx();
		m_PriceDate = invoiceLine.getC_Invoice().getDateInvoiced();
		this.trxName = trxName;
	}
	
	@Override
	public void setProjectLine(I_C_ProjectLine projectLine, String trxName) {
		m_M_Product_ID = projectLine.getM_Product_ID();
		m_C_BPartner_ID = projectLine.getC_Project().getC_BPartner_ID();
		BigDecimal qty = projectLine.getPlannedQty();
		if (qty != null && Env.ZERO.compareTo(qty) != 0)
			m_Qty = qty;
		m_isSOTrx = true;
		this.trxName = trxName;
	}
	
	@Override
	public void setRequisitionLine(I_M_RequisitionLine reqLine, String trxName) {
		m_M_Product_ID = reqLine.getM_Product_ID();
		m_C_BPartner_ID = reqLine.getC_BPartner_ID();
		BigDecimal qty = reqLine.getQty();
		if (qty != null && Env.ZERO.compareTo(qty) != 0)
			m_Qty = qty;
		m_isSOTrx = false;
		this.trxName = trxName;
	}
	
	@Override
	public void setRMALine(I_M_RMALine rmaLine, String trxName) {
		m_M_Product_ID = rmaLine.getM_Product_ID();
		m_C_BPartner_ID = rmaLine.getM_RMA().getC_BPartner_ID();
		m_Qty = Env.ONE;
		m_isSOTrx = rmaLine.getM_RMA().isSOTrx();
		this.trxName = trxName;
	}
}
