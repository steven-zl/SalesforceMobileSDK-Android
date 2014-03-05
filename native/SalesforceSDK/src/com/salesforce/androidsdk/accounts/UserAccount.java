/*
 * Copyright (c) 2014, salesforce.com, inc.
 * All rights reserved.
 * Redistribution and use of this software in source and binary forms, with or
 * without modification, are permitted provided that the following conditions
 * are met:
 * - Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * - Neither the name of salesforce.com, inc. nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission of salesforce.com, inc.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.salesforce.androidsdk.accounts;

import android.text.TextUtils;

/**
 * This class represents a single user account that is currently
 * logged in against a Salesforce endpoint. It encapsulates data
 * that is used to uniquely identify a single user account.
 *
 * @author bhariharan
 */
public class UserAccount {

	private static final String INTERNAL_COMMUNITY_PATH = "internal";
	private static final String FORWARD_SLASH = "/";
	private static final String UNDERSCORE = "_";

	/*
	 * TODO: Add user profile picture, community ID, community name,
	 * and org name fields here.
	 */
	private String authToken;
	private String refreshToken;
	private String loginServer;
	private String idUrl;
	private String instanceServer;
	private String orgId;
	private String userId;
	private String username;
	private String accountName;
	private String clientId;

	/**
	 * Parameterized constructor.
	 *
	 * @param authToken Auth token.
	 * @param refreshToken Refresh token.
	 * @param loginServer Login server.
	 * @param idUrl Identity URL.
	 * @param instanceServer Instance server.
	 * @param orgId Org ID.
	 * @param userId User ID.
	 * @param username Username.
	 * @param accountName Account name.
	 * @param clientId Client ID.
	 */
	public UserAccount(String authToken, String refreshToken,
			String loginServer, String idUrl, String instanceServer,
			String orgId, String userId, String username, String accountName,
			String clientId) {
		this.authToken = authToken;
		this.refreshToken = refreshToken;
		this.loginServer = loginServer;
		this.idUrl = idUrl;
		this.instanceServer = instanceServer;
		this.orgId = orgId;
		this.userId = userId;
		this.username = username;
		this.accountName = accountName;
		this.clientId = clientId;
	}

	/**
	 * Returns the auth token for this user account.
	 *
	 * @return Auth token.
	 */
	public String getAuthToken() {
		return authToken;
	}

	/**
	 * Returns the refresh token for this user account.
	 *
	 * @return Refresh token.
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * Returns the login server for this user account.
	 *
	 * @return Login server.
	 */
	public String getLoginServer() {
		return loginServer;
	}

	/**
	 * Returns the identity URL for this user account.
	 *
	 * @return Identity URL.
	 */
	public String getIdUrl() {
		return idUrl;
	}

	/**
	 * Returns the instance server for this user account.
	 *
	 * @return Instance server.
	 */
	public String getInstanceServer() {
		return instanceServer;
	}

	/**
	 * Returns the org ID for this user account.
	 *
	 * @return Org ID.
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * Returns the user ID for this user account.
	 *
	 * @return User ID.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Returns the username for this user account.
	 *
	 * @return Username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Returns the account name for this user account.
	 *
	 * @return Account name.
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * Returns the client ID for this user account.
	 *
	 * @return Client ID.
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * Returns the org level storage path for this user account, relative to
	 * the higher level directory of app data. The higher level directory
	 * could be 'databases', or 'files'. The output is of the format
	 * '/<orgID>/'. This storage path is meant for data that can be shared
	 * across multiple users of the same org.
	 *
	 * @return File storage path.
	 */
	public String getOrgLevelStoragePath() {
		final StringBuffer sb = new StringBuffer(FORWARD_SLASH);
		sb.append(orgId);
		sb.append(FORWARD_SLASH);
		return sb.toString();
	}

	/**
	 * Returns the user level storage path for this user account, relative to
	 * the higher level directory of app data. The higher level directory
	 * could be 'databases', or 'files'. The output is of the format
	 * '/<orgID>/<userId>/'. This storage path is meant for data that is unique
	 * to a particular user in an org, but common across all the communities
	 * that the user is a member of within that org.
	 *
	 * @return File storage path.
	 */
	public String getUserLevelStoragePath() {
		final StringBuffer sb = new StringBuffer(FORWARD_SLASH);
		sb.append(orgId);
		sb.append(FORWARD_SLASH);
		sb.append(userId);
		sb.append(FORWARD_SLASH);
		return sb.toString();
	}

	/**
	 * Returns the storage path for this user account, relative to the higher
	 * level directory of app data. The higher level directory could be
	 * 'databases', or 'files'. The output is of the format
	 * '/<orgID>/<userID>/<communityID>/'. If 'communityID' is null, then the
	 * output would be '/<orgID>/<userID>/internal/'. This storage path is meant
	 * for data that is unique to a particular user in a specific community.
	 *
	 * @param communityId Community ID. Pass 'null' for internal community.
	 * @return File storage path.
	 */
	public String getCommunityLevelStoragePath(String communityId) {
		final StringBuffer sb = new StringBuffer(FORWARD_SLASH);
		sb.append(orgId);
		sb.append(FORWARD_SLASH);
		sb.append(userId);
		sb.append(FORWARD_SLASH);
		String leafDir = INTERNAL_COMMUNITY_PATH;
		if (!TextUtils.isEmpty(communityId)) {
			leafDir = communityId;
		}
		sb.append(leafDir);
		sb.append(FORWARD_SLASH);
		return sb.toString();
	}

	/**
	 * Returns a unique suffix for this user account, that can be appended
	 * to a shared preference file to uniquely identify this account, at an
	 * org level. The output is of the format '_<orgID>'. This suffix is meant
	 * for data that can be shared across multiple users of the same org.
	 *
	 * @return Shared preference suffix.
	 */
	public String getOrgLevelSharedPrefSuffix() {
		final StringBuffer sb = new StringBuffer(UNDERSCORE);
		sb.append(orgId);
		return sb.toString();
	}

	/**
	 * Returns a unique suffix for this user account, that can be appended
	 * to a shared preference file to uniquely identify this account, at a
	 * user level. The output is of the format '_<orgID>_<userID>'. This suffix
	 * is meant for data that is unique to a particular user in an org,
	 * but common across all the communities that the user is a member
	 * of within that org.
	 *
	 * @return Shared preference suffix.
	 */
	public String getUserLevelSharedPrefSuffix() {
		final StringBuffer sb = new StringBuffer(UNDERSCORE);
		sb.append(orgId);
		sb.append(UNDERSCORE);
		sb.append(userId);
		return sb.toString();
	}

	/**
	 * Returns a unique suffix for this user account, that can be appended
	 * to a shared preference file to uniquely identify this account, at a
	 * community level. The output is of the format '_<orgID>_<userID>_<communityID>'.
	 * If 'communityID' is null, then the output would be '_<orgID>_<userID>_internal'.
	 * This suffix is meant for data that is unique to a particular
	 * user in a specific community.
	 *
	 * @param communityId Community ID. Pass 'null' for internal community.
	 * @return Shared preference suffix.
	 */
	public String getCommunityLevelSharedPrefSuffix(String communityId) {
		final StringBuffer sb = new StringBuffer(UNDERSCORE);
		sb.append(orgId);
		sb.append(UNDERSCORE);
		sb.append(userId);
		sb.append(UNDERSCORE);
		String leafDir = INTERNAL_COMMUNITY_PATH;
		if (!TextUtils.isEmpty(communityId)) {
			leafDir = communityId;
		}
		sb.append(leafDir);
		return sb.toString();
	}

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof UserAccount)) {
            return false;
        }
        final UserAccount userAccount = (UserAccount) object;
        if (userId == null || orgId == null || userAccount.getUserId() == null
        		|| userAccount.getOrgId() == null) {
        	return false;
        }
        if (userAccount.getUserId().equals(userId) && userAccount.getOrgId().equals(orgId)) {
        	return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result ^= orgId.hashCode() + result * 37;
        return result;
    }
}
