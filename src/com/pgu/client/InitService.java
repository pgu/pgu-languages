package com.pgu.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("init")
public interface InitService extends RemoteService {

    void initData();

    void deleteData();

}
