package cn.fate.ssm.service.impl;

import cn.fate.ssm.beans.ClassesType;
import cn.fate.ssm.mapper.ClassesTypeMapper;
import cn.fate.ssm.service.IClassesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fate
 * @date 2019-06-05 21:09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public  class ClassesTypeServiceImpl implements IClassesTypeService {
    private final ClassesTypeMapper mapper;
    @Autowired
    public ClassesTypeServiceImpl(ClassesTypeMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean save(ClassesType classesType) {
        return mapper.save(classesType) >0;
    }
}
