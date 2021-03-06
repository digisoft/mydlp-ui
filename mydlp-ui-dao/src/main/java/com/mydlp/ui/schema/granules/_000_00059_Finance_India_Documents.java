package com.mydlp.ui.schema.granules;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.mydlp.ui.dao.DAOUtil;
import com.mydlp.ui.domain.BundledKeywordGroup;
import com.mydlp.ui.domain.DataFormat;
import com.mydlp.ui.domain.InformationDescription;
import com.mydlp.ui.domain.InformationFeature;
import com.mydlp.ui.domain.InformationType;
import com.mydlp.ui.domain.InventoryBase;
import com.mydlp.ui.domain.InventoryCategory;
import com.mydlp.ui.domain.InventoryItem;
import com.mydlp.ui.domain.Matcher;
import com.mydlp.ui.domain.MatcherArgument;
import com.mydlp.ui.domain.NonCascadingArgument;
import com.mydlp.ui.schema.AbstractGranule;

public class _000_00059_Finance_India_Documents extends AbstractGranule {

	@Override
	protected void callback() {
			
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(InventoryCategory.class)
					.add(Restrictions.eq("nameKey", "inventory.compliance.finance.predefined"));
		@SuppressWarnings("unchecked")
		List<InventoryCategory> list = getHibernateTemplate().findByCriteria(criteria);
		InventoryCategory finance = DAOUtil.getSingleResult(list);
		
		DetachedCriteria criteria2 = 
				DetachedCriteria.forClass(DataFormat.class)
					.add(Restrictions.eq("nameKey", "dataFormat.all.label"));
		@SuppressWarnings("unchecked")
		List<DataFormat> list2 = getHibernateTemplate().findByCriteria(criteria2);
		DataFormat df = DAOUtil.getSingleResult(list2);
		
		DetachedCriteria criteria3 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("nameKey", "finance.indiaDocuments.form16.keywordList"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list3 = getHibernateTemplate().findByCriteria(criteria3);
		BundledKeywordGroup form16 = DAOUtil.getSingleResult(list3);
		
		DetachedCriteria criteria4 = 
				DetachedCriteria.forClass(BundledKeywordGroup.class)
					.add(Restrictions.eq("nameKey", "finance.indiaDocuments.form16A.keywordList"));
		@SuppressWarnings("unchecked")
		List<BundledKeywordGroup> list4 = getHibernateTemplate().findByCriteria(criteria4);
		BundledKeywordGroup form16A = DAOUtil.getSingleResult(list4);
		
		InventoryCategory indiaDocuments = new InventoryCategory();
		indiaDocuments.setNameKey("inventory.compliance.finance.india_documents.predefined");
		indiaDocuments.setEditable(false);
		indiaDocuments.setCategory(finance);
	
		/*India Form 16*/
		
		Matcher matcher = new Matcher();
		matcher.setFunctionName("keyword_group");
		
		NonCascadingArgument nonCascadingArgument = new NonCascadingArgument(); 	
		MatcherArgument matcherArgument = new MatcherArgument();
		nonCascadingArgument.setArgument(form16);
		matcherArgument.setCoupledMatcher(matcher);
		matcherArgument.setCoupledArgument(nonCascadingArgument);
		List<MatcherArgument> matcherArguments = new ArrayList<MatcherArgument>();
		matcherArguments.add(matcherArgument);
		matcher.setMatcherArguments(matcherArguments);
		
		InformationFeature informationFeature = new InformationFeature();
		informationFeature.setThreshold(new Long(10));
		informationFeature.setMatcher(matcher);
		matcher.setCoupledInformationFeature(informationFeature);
		
		InformationDescription informationDescription = new InformationDescription();
		List<InformationFeature> ifts = new ArrayList<InformationFeature>();
		informationDescription.setDistanceEnabled(true);
		informationDescription.setDistance(2500);
		ifts.add(informationFeature);
		informationDescription.setFeatures(ifts);
		
		InformationType informationType = new InformationType();
		informationType.setInformationDescription(informationDescription);
		List<DataFormat> dfs = new ArrayList<DataFormat>();
		dfs.add(df);
		informationType.setDataFormats(dfs);
		
		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setNameKey("informationType.compliance.finance.indiaDocuments.form16");
		inventoryItem.setItem(informationType);
		informationType.setCoupledInventoryItem(inventoryItem);
		
		/*India Form 16-A*/
		Matcher matcherA = new Matcher();
		matcherA.setFunctionName("keyword_group");
		
		NonCascadingArgument nonCascadingArgumentA = new NonCascadingArgument(); 	
		MatcherArgument matcherArgumentA = new MatcherArgument();
		nonCascadingArgumentA.setArgument(form16A);
		matcherArgumentA.setCoupledMatcher(matcherA);
		matcherArgumentA.setCoupledArgument(nonCascadingArgumentA);
		List<MatcherArgument> matcherArgumentsA = new ArrayList<MatcherArgument>();
		matcherArgumentsA.add(matcherArgumentA);
		matcherA.setMatcherArguments(matcherArgumentsA);
		
		InformationFeature informationFeatureA = new InformationFeature();
		informationFeatureA.setThreshold(new Long(10));
		informationFeatureA.setMatcher(matcherA);
		matcherA.setCoupledInformationFeature(informationFeatureA);
		
		InformationDescription informationDescriptionA = new InformationDescription();
		List<InformationFeature> iftsA = new ArrayList<InformationFeature>();
		informationDescriptionA.setDistanceEnabled(true);
		informationDescriptionA.setDistance(2500);
		iftsA.add(informationFeatureA);
		informationDescriptionA.setFeatures(iftsA);
		
		InformationType informationTypeA = new InformationType();
		informationTypeA.setInformationDescription(informationDescriptionA);
		informationTypeA.setDataFormats(dfs);
		
		InventoryItem inventoryItemA = new InventoryItem();
		inventoryItemA.setNameKey("informationType.compliance.finance.indiaDocuments.form16A");
		inventoryItemA.setItem(informationTypeA);
		informationTypeA.setCoupledInventoryItem(inventoryItemA);
		

		inventoryItem.setCategory(indiaDocuments);
		inventoryItemA.setCategory(indiaDocuments);
		List<InventoryBase> dtc = new ArrayList<InventoryBase>();
		dtc.add(inventoryItem);
		dtc.add(inventoryItemA);
		indiaDocuments.setChildren(dtc);
		
		getHibernateTemplate().saveOrUpdate(finance);
		getHibernateTemplate().saveOrUpdate(indiaDocuments);
		getHibernateTemplate().saveOrUpdate(inventoryItem);
		getHibernateTemplate().saveOrUpdate(inventoryItemA);
	}
}
