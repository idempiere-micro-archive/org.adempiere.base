/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/** Generated Interface for A_Depreciation_Table_Detail
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
public interface I_A_Depreciation_Table_Detail 
{

    /** TableName=A_Depreciation_Table_Detail */
    public static final String Table_Name = "A_Depreciation_Table_Detail";

    /** AD_Table_ID=53113 */
    public static final int Table_ID = 53113;

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name A_Depreciation_Rate */
    public static final String COLUMNNAME_A_Depreciation_Rate = "A_Depreciation_Rate";

	/** Set Rate	  */
	public void setA_Depreciation_Rate (BigDecimal A_Depreciation_Rate);

	/** Get Rate	  */
	public BigDecimal getA_Depreciation_Rate();

    /** Column name A_Depreciation_Table_Code */
    public static final String COLUMNNAME_A_Depreciation_Table_Code = "A_Depreciation_Table_Code";

	/** Set Depreciation Code	  */
	public void setA_Depreciation_Table_Code (String A_Depreciation_Table_Code);

	/** Get Depreciation Code	  */
	public String getA_Depreciation_Table_Code();

    /** Column name A_Depreciation_Table_Detail_ID */
    public static final String COLUMNNAME_A_Depreciation_Table_Detail_ID = "A_Depreciation_Table_Detail_ID";

	/** Set Depreciation Table Detail	  */
	public void setA_Depreciation_Table_Detail_ID (int A_Depreciation_Table_Detail_ID);

	/** Get Depreciation Table Detail	  */
	public int getA_Depreciation_Table_Detail_ID();

    /** Column name A_Depreciation_Table_Detail_UU */
    public static final String COLUMNNAME_A_Depreciation_Table_Detail_UU = "A_Depreciation_Table_Detail_UU";

	/** Set A_Depreciation_Table_Detail_UU	  */
	public void setA_Depreciation_Table_Detail_UU (String A_Depreciation_Table_Detail_UU);

	/** Get A_Depreciation_Table_Detail_UU	  */
	public String getA_Depreciation_Table_Detail_UU();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name A_Period */
    public static final String COLUMNNAME_A_Period = "A_Period";

	/** Set Asset Period	  */
	public void setA_Period (int A_Period);

	/** Get Asset Period	  */
	public int getA_Period();

    /** Column name A_Table_Rate_Type */
    public static final String COLUMNNAME_A_Table_Rate_Type = "A_Table_Rate_Type";

	/** Set Type	  */
	public void setA_Table_Rate_Type (String A_Table_Rate_Type);

	/** Get Type	  */
	public String getA_Table_Rate_Type();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
