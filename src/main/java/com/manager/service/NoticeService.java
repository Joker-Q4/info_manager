package com.manager.service;

import com.manager.entity.Notice;

public interface NoticeService {

    Notice getNotice();
    boolean modifyNotice(Notice notice);
}
