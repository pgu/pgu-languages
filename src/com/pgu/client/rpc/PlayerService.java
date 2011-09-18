package com.pgu.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pgu.shared.UserAccount;

@RemoteServiceRelativePath("player")
public interface PlayerService extends RemoteService {

    int getScore(UserAccount user);

}
