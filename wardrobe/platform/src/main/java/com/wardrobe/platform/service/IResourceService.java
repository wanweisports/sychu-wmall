package com.wardrobe.platform.service;

import com.wardrobe.common.po.SysResources;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */
public interface IResourceService {

    List<SysResources> getKeyResources(List<SysResources> resources);

    void save(SysResources resource);

    void delete(int resourceId);

    SysResources deleteAndReturn(int resourceId);

    void delete(int resourceServiceId, String resourceServiceType);

    SysResources getResourceById(int resourceId);

    List<Integer> saveResources(List<SysResources> resources);

    void updateResources(String[] resourceIds, int resourceServiceId, String resourceServiceType, String resourceType);

    SysResources getResource(int resourceServiceId, String resourceServiceType);

    SysResources getResource(int resourceServiceId, String resourceServiceType, int resourceSeq);

    SysResources getResourceByParentId(int resourceServiceParentId, String resourceServiceType, int resourceSeq);

    SysResources saveResource(MultipartFile multipartFile, int serviceId, String serviceType, String resourceType, String fold);
}
