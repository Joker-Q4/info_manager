package com.manager.service.impl;

import com.manager.entity.Notice;
import com.manager.mapper.NoticeMapper;
import com.manager.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Resource
    NoticeMapper noticeMapper;

    @Override
    public Notice getNotice() {
        return noticeMapper.queryNotice();
    }

    @Override
    public boolean modifyNotice(Notice notice) {
        return noticeMapper.updateNotice(notice) > 0;
    }
}
