package co.com.cybersoft.core.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 
 * @author Yuan Ji and Daniel Falla
 *
 * @param <T>
 */
public class PageWrapper<T> {
    public static final int MAX_PAGE_ITEM_DISPLAY = 3;
    private Page<T> page;
    private List<PageItem> items;
    private int number;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PageWrapper(Page<T> page, String url){
        this.page = page;
        this.url = url;
        items = new ArrayList<PageItem>();

        number = page.getNumber() + 1; //start from 1 to match page.page

        int start, size;
        if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY){
            start = 1;
            size = page.getTotalPages();
        } else {
            if (number <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY/2){
                start = 1;
                size = MAX_PAGE_ITEM_DISPLAY;
            } else if (number >= page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY/2){
                start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 1;
                size = MAX_PAGE_ITEM_DISPLAY;
            } else {
                start = number - MAX_PAGE_ITEM_DISPLAY/2;
                size = MAX_PAGE_ITEM_DISPLAY;
            }
        }

        for (int i = 0; i<size; i++){
            items.add(new PageItem(start+i, (start+i)==number));
        }
    }

    public List<PageItem> getItems(){
        return items;
    }

    public int getNumber(){
        return number;
    }
    
    public void setNumber(int number){
    	this.number=number;
    }

    public List<T> getContent(){
        return page.getContent();
    }

    public int getSize(){
        return page.getSize();
    }

    public int getTotalPages(){
        return page.getTotalPages();
    }

    public boolean isFirstPage(){
        return page.isFirstPage();
    }

    public boolean isLastPage(){
        return page.isLastPage();
    }

    public boolean isHasPreviousPage(){
        return page.hasPreviousPage();
    }

    public boolean isHasNextPage(){
        return page.hasNextPage();
    }

    public class PageItem {
        private int number;
        private boolean current;
        public PageItem(int number, boolean current){
            this.number = number;
            this.current = current;
        }

        public int getNumber(){
            return this.number;
        }

        public boolean isCurrent(){
            return this.current;
        }
    }
}