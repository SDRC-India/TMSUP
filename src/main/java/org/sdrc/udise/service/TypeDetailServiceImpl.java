package org.sdrc.udise.service;

import java.util.ArrayList;
import java.util.List;

import org.sdrc.udise.domain.TypeDetail;
import org.sdrc.udise.model.TypeDetailModel;
import org.sdrc.udise.repository.TypeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Azaruddin (azaruddin@sdrc.co.in)
 *
 */

@Service
public class TypeDetailServiceImpl implements TypeDetailService {

	@Autowired
	private TypeDetailsRepository typeDetailsRepository;

	@Value("#{'${school.has.secondary.id}'.split(',')}")
	private List<Integer> typeIdsOfSecondarySchools;

	@Override
	public List<TypeDetailModel> findAllSecondarySchoolTypeComboList() {

		List<TypeDetail> details = typeDetailsRepository.findByTypeDetailIdIn(typeIdsOfSecondarySchools);

		List<TypeDetailModel> models = new ArrayList<>();
		if (details != null) {
			for (TypeDetail typeDetail : details) {
				TypeDetailModel model = new TypeDetailModel();
				model.setDescription(typeDetail.getDescription());
				model.setTypeDetail(typeDetail.getTypeDetail());
				model.setTypeDetailId(typeDetail.getTypeDetailId());
				models.add(model);
			}
		}

		return models;
	}

}
