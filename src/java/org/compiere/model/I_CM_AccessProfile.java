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
 * or via info@compiere.org or http://www.idempiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.idempiere.common.util.KeyNamePair;

/** Generated Interface for CM_AccessProfile
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
public interface I_CM_AccessProfile 
{

    /** TableName=CM_AccessProfile */
    public static final String Table_Name = "CM_AccessProfile";

    /** AD_Table_ID=885 */
    public static final int Table_ID = 885;

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

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

    /** Column name CM_AccessProfile_ID */
    public static final String COLUMNNAME_CM_AccessProfile_ID = "CM_AccessProfile_ID";

	/** Set Web Access Profile.
	  * Web Access Profile
	  */
	public void setCM_AccessProfile_ID (int CM_AccessProfile_ID);

	/** Get Web Access Profile.
	  * Web Access Profile
	  */
	public int getCM_AccessProfile_ID();

    /** Column name CM_AccessProfile_UU */
    public static final String COLUMNNAME_CM_AccessProfile_UU = "CM_AccessProfile_UU";

	/** Set CM_AccessProfile_UU	  */
	public void setCM_AccessProfile_UU (String CM_AccessProfile_UU);

	/** Get CM_AccessProfile_UU	  */
	public String getCM_AccessProfile_UU();

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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

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

    /** Column name IsExclude */
    public static final String COLUMNNAME_IsExclude = "IsExclude";

	/** Set Exclude.
	  * Exclude access to the data - if not selected Include access to the data
	  */
	public void setIsExclude (boolean IsExclude);

	/** Get Exclude.
	  * Exclude access to the data - if not selected Include access to the data
	  */
	public boolean isExclude();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

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
