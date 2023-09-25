package ru.mikheev.kirill.hw34customwebserver.processing;

import ru.mikheev.kirill.hw34customwebserver.exception.PageNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Load and cache pages content
 */
public class PageResolver {

    private static final String PAGES_DIRECTORY = "pages";
    private static final String ERROR_PAGE_KEY = "_ERROR";

    private final Map<String, String> pagesContent = new ConcurrentHashMap<>();

    public PageResolver() {
        initErrorPage();
    }

    /**
     * Load page by its name and return its content
     *
     * @param pageName - name of required page
     * @return content of required page
     * @throws PageNotFoundException if page was not found
     */
    public String getPageContentByPath(String pageName) {
        return pagesContent.computeIfAbsent(pageName, this::loadPageContent);
    }

    /**
     * Form error page with specific error
     *
     * @param errorMessage - message, that will be shown to user
     * @return content of error page
     */
    public String getErrorPage(String errorMessage) {
        return String.format(pagesContent.get(ERROR_PAGE_KEY), errorMessage);
    }

    private String loadPageContent(String pageName) {
        StringBuilder pageStringBuilder = new StringBuilder();
        InputStream resourceStream = this.getClass().getClassLoader()
                .getResourceAsStream(PAGES_DIRECTORY + '/' + pageName);
        if (resourceStream == null) {
            throw new PageNotFoundException();
        }
        try (BufferedReader pageReader = new BufferedReader(new InputStreamReader(
                resourceStream
        ))) {
            String newLine;
            while ((newLine = pageReader.readLine()) != null) {
                pageStringBuilder.append(newLine);
            }
            return pageStringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error during page loading");
        }
    }

    private void initErrorPage() {
        pagesContent.put(ERROR_PAGE_KEY,
                "<html><body>" +
                        "%s" +
                        "</body></html>"
        );
    }
}
