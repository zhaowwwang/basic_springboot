package com.basic.core.bean.system.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: wangzw
 * @Date: 2017/10/1 19:56
 * @Description: 系统全部的菜单
 * @Version: 1.0
 */
public class SystemResourceVo implements Serializable {

    private int id;
    private String resourceName;
    private boolean isSelect;
    private List<SecondResource> secondResourceList;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public List<SecondResource> getSecondResourceList() {
        return secondResourceList;
    }

    public void setSecondResourceList(List<SecondResource> secondResourceList) {
        this.secondResourceList = secondResourceList;
    }

    public class SecondResource implements Serializable{
        private int id;
        private String resourceName;
        private boolean isSelect;
        private List<ButtonResource> buttonResourceList;

        public List<ButtonResource> getButtonResourceList() {
            return buttonResourceList;
        }

        public void setButtonResourceList(List<ButtonResource> buttonResourceList) {
            this.buttonResourceList = buttonResourceList;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getResourceName() {
            return resourceName;
        }

        public void setResourceName(String resourceName) {
            this.resourceName = resourceName;
        }
    }

    public class ButtonResource implements Serializable{
        private int id;
        private String resourceName;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getResourceName() {
            return resourceName;
        }

        public void setResourceName(String resourceName) {
            this.resourceName = resourceName;
        }
    }


}
