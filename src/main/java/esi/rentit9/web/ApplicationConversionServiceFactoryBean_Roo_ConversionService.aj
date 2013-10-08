// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package esi.rentit9.web;

import esi.rentit9.domain.BuildIt;
import esi.rentit9.domain.Invoice;
import esi.rentit9.domain.Plant;
import esi.rentit9.domain.PurchaseOrder;
import esi.rentit9.domain.PurchaseOrderLine;
import esi.rentit9.domain.RemittanceAdvice;
import esi.rentit9.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    public Converter<BuildIt, String> ApplicationConversionServiceFactoryBean.getBuildItToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<esi.rentit9.domain.BuildIt, java.lang.String>() {
            public String convert(BuildIt buildIt) {
                return new StringBuilder().append(buildIt.getName()).append(' ').append(buildIt.getUrl()).toString();
            }
        };
    }
    
    public Converter<Long, BuildIt> ApplicationConversionServiceFactoryBean.getIdToBuildItConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, esi.rentit9.domain.BuildIt>() {
            public esi.rentit9.domain.BuildIt convert(java.lang.Long id) {
                return BuildIt.findBuildIt(id);
            }
        };
    }
    
    public Converter<String, BuildIt> ApplicationConversionServiceFactoryBean.getStringToBuildItConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, esi.rentit9.domain.BuildIt>() {
            public esi.rentit9.domain.BuildIt convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), BuildIt.class);
            }
        };
    }
    
    public Converter<Invoice, String> ApplicationConversionServiceFactoryBean.getInvoiceToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<esi.rentit9.domain.Invoice, java.lang.String>() {
            public String convert(Invoice invoice) {
                return new StringBuilder().append(invoice.getDueDate()).toString();
            }
        };
    }
    
    public Converter<Long, Invoice> ApplicationConversionServiceFactoryBean.getIdToInvoiceConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, esi.rentit9.domain.Invoice>() {
            public esi.rentit9.domain.Invoice convert(java.lang.Long id) {
                return Invoice.findInvoice(id);
            }
        };
    }
    
    public Converter<String, Invoice> ApplicationConversionServiceFactoryBean.getStringToInvoiceConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, esi.rentit9.domain.Invoice>() {
            public esi.rentit9.domain.Invoice convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Invoice.class);
            }
        };
    }
    
    public Converter<Plant, String> ApplicationConversionServiceFactoryBean.getPlantToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<esi.rentit9.domain.Plant, java.lang.String>() {
            public String convert(Plant plant) {
                return new StringBuilder().append(plant.getName()).append(' ').append(plant.getDescription()).append(' ').append(plant.getPrice()).toString();
            }
        };
    }
    
    public Converter<Long, Plant> ApplicationConversionServiceFactoryBean.getIdToPlantConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, esi.rentit9.domain.Plant>() {
            public esi.rentit9.domain.Plant convert(java.lang.Long id) {
                return Plant.findPlant(id);
            }
        };
    }
    
    public Converter<String, Plant> ApplicationConversionServiceFactoryBean.getStringToPlantConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, esi.rentit9.domain.Plant>() {
            public esi.rentit9.domain.Plant convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Plant.class);
            }
        };
    }
    
    public Converter<PurchaseOrder, String> ApplicationConversionServiceFactoryBean.getPurchaseOrderToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<esi.rentit9.domain.PurchaseOrder, java.lang.String>() {
            public String convert(PurchaseOrder purchaseOrder) {
                return new StringBuilder().append(purchaseOrder.getSiteAddress()).toString();
            }
        };
    }
    
    public Converter<Long, PurchaseOrder> ApplicationConversionServiceFactoryBean.getIdToPurchaseOrderConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, esi.rentit9.domain.PurchaseOrder>() {
            public esi.rentit9.domain.PurchaseOrder convert(java.lang.Long id) {
                return PurchaseOrder.findPurchaseOrder(id);
            }
        };
    }
    
    public Converter<String, PurchaseOrder> ApplicationConversionServiceFactoryBean.getStringToPurchaseOrderConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, esi.rentit9.domain.PurchaseOrder>() {
            public esi.rentit9.domain.PurchaseOrder convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PurchaseOrder.class);
            }
        };
    }
    
    public Converter<PurchaseOrderLine, String> ApplicationConversionServiceFactoryBean.getPurchaseOrderLineToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<esi.rentit9.domain.PurchaseOrderLine, java.lang.String>() {
            public String convert(PurchaseOrderLine purchaseOrderLine) {
                return new StringBuilder().append(purchaseOrderLine.getStartDate()).append(' ').append(purchaseOrderLine.getEndDate()).append(' ').append(purchaseOrderLine.getTotal()).toString();
            }
        };
    }
    
    public Converter<Long, PurchaseOrderLine> ApplicationConversionServiceFactoryBean.getIdToPurchaseOrderLineConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, esi.rentit9.domain.PurchaseOrderLine>() {
            public esi.rentit9.domain.PurchaseOrderLine convert(java.lang.Long id) {
                return PurchaseOrderLine.findPurchaseOrderLine(id);
            }
        };
    }
    
    public Converter<String, PurchaseOrderLine> ApplicationConversionServiceFactoryBean.getStringToPurchaseOrderLineConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, esi.rentit9.domain.PurchaseOrderLine>() {
            public esi.rentit9.domain.PurchaseOrderLine convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PurchaseOrderLine.class);
            }
        };
    }
    
    public Converter<RemittanceAdvice, String> ApplicationConversionServiceFactoryBean.getRemittanceAdviceToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<esi.rentit9.domain.RemittanceAdvice, java.lang.String>() {
            public String convert(RemittanceAdvice remittanceAdvice) {
                return new StringBuilder().append(remittanceAdvice.getPayDay()).toString();
            }
        };
    }
    
    public Converter<Long, RemittanceAdvice> ApplicationConversionServiceFactoryBean.getIdToRemittanceAdviceConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, esi.rentit9.domain.RemittanceAdvice>() {
            public esi.rentit9.domain.RemittanceAdvice convert(java.lang.Long id) {
                return RemittanceAdvice.findRemittanceAdvice(id);
            }
        };
    }
    
    public Converter<String, RemittanceAdvice> ApplicationConversionServiceFactoryBean.getStringToRemittanceAdviceConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, esi.rentit9.domain.RemittanceAdvice>() {
            public esi.rentit9.domain.RemittanceAdvice convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), RemittanceAdvice.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getBuildItToStringConverter());
        registry.addConverter(getIdToBuildItConverter());
        registry.addConverter(getStringToBuildItConverter());
        registry.addConverter(getInvoiceToStringConverter());
        registry.addConverter(getIdToInvoiceConverter());
        registry.addConverter(getStringToInvoiceConverter());
        registry.addConverter(getPlantToStringConverter());
        registry.addConverter(getIdToPlantConverter());
        registry.addConverter(getStringToPlantConverter());
        registry.addConverter(getPurchaseOrderToStringConverter());
        registry.addConverter(getIdToPurchaseOrderConverter());
        registry.addConverter(getStringToPurchaseOrderConverter());
        registry.addConverter(getPurchaseOrderLineToStringConverter());
        registry.addConverter(getIdToPurchaseOrderLineConverter());
        registry.addConverter(getStringToPurchaseOrderLineConverter());
        registry.addConverter(getRemittanceAdviceToStringConverter());
        registry.addConverter(getIdToRemittanceAdviceConverter());
        registry.addConverter(getStringToRemittanceAdviceConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
