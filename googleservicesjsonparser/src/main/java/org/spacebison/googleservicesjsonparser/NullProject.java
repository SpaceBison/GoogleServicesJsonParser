package org.spacebison.googleservicesjsonparser;

import org.gradle.api.Action;
import org.gradle.api.AntBuilder;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.NamedDomainObjectFactory;
import org.gradle.api.PathValidation;
import org.gradle.api.Project;
import org.gradle.api.ProjectState;
import org.gradle.api.Task;
import org.gradle.api.UnknownProjectException;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.artifacts.dsl.ArtifactHandler;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.component.SoftwareComponentContainer;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.ConfigurableFileTree;
import org.gradle.api.file.CopySpec;
import org.gradle.api.file.DeleteSpec;
import org.gradle.api.file.FileTree;
import org.gradle.api.file.ProjectLayout;
import org.gradle.api.initialization.dsl.ScriptHandler;
import org.gradle.api.invocation.Gradle;
import org.gradle.api.logging.LoggingManager;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.plugins.Convention;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.plugins.ObjectConfigurationAction;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.plugins.PluginManager;
import org.gradle.api.provider.PropertyState;
import org.gradle.api.provider.Provider;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.resources.ResourceHandler;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.WorkResult;
import org.gradle.normalization.InputNormalizationHandler;
import org.gradle.process.JavaExecSpec;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.Nullable;

import groovy.lang.Closure;
import groovy.lang.MissingPropertyException;

public abstract class NullProject implements Project {
    @Override
    public PluginContainer getPlugins() {
        return null;
    }

    @Override
    public void apply(Closure closure) {

    }

    @Override
    public void apply(Action<? super ObjectConfigurationAction> action) {

    }

    @Override
    public void apply(Map<String, ?> options) {

    }

    @Override
    public PluginManager getPluginManager() {
        return null;
    }

    @Override
    public int compareTo(Project project) {
        return 0;
    }

    @Override
    public Project getRootProject() {
        return null;
    }

    @Override
    public File getRootDir() {
        return null;
    }

    @Override
    public File getBuildDir() {
        return null;
    }

    @Override
    public void setBuildDir(File path) {

    }

    @Override
    public void setBuildDir(Object path) {

    }

    @Override
    public File getBuildFile() {
        return null;
    }

    @Override
    public Project getParent() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public Object getGroup() {
        return null;
    }

    @Override
    public void setGroup(Object group) {

    }

    @Override
    public Object getVersion() {
        return null;
    }

    @Override
    public void setVersion(Object version) {

    }

    @Override
    public Object getStatus() {
        return null;
    }

    @Override
    public void setStatus(Object status) {

    }

    @Override
    public Map<String, Project> getChildProjects() {
        return null;
    }

    @Override
    public void setProperty(String name, Object value) throws MissingPropertyException {

    }

    @Override
    public Project getProject() {
        return null;
    }

    @Override
    public Set<Project> getAllprojects() {
        return null;
    }

    @Override
    public Set<Project> getSubprojects() {
        return null;
    }

    @Override
    public Task task(String name) throws InvalidUserDataException {
        return null;
    }

    @Override
    public Task task(Map<String, ?> args, String name) throws InvalidUserDataException {
        return null;
    }

    @Override
    public Task task(Map<String, ?> args, String name, Closure configureClosure) {
        return null;
    }

    @Override
    public Task task(String name, Closure configureClosure) {
        return null;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public List<String> getDefaultTasks() {
        return null;
    }

    @Override
    public void setDefaultTasks(List<String> defaultTasks) {

    }

    @Override
    public void defaultTasks(String... defaultTasks) {

    }

    @Override
    public Project evaluationDependsOn(String path) throws UnknownProjectException {
        return null;
    }

    @Override
    public void evaluationDependsOnChildren() {

    }

    @Override
    public Project findProject(String path) {
        return null;
    }

    @Override
    public Project project(String path) throws UnknownProjectException {
        return null;
    }

    @Override
    public Project project(String path, Closure configureClosure) {
        return null;
    }

    @Override
    public Project project(String path, Action<? super Project> configureAction) {
        return null;
    }

    @Override
    public Map<Project, Set<Task>> getAllTasks(boolean recursive) {
        return null;
    }

    @Override
    public Set<Task> getTasksByName(String name, boolean recursive) {
        return null;
    }

    @Override
    public File getProjectDir() {
        return null;
    }

    @Override
    public File file(Object path) {
        return null;
    }

    @Override
    public File file(Object path, PathValidation validation) throws InvalidUserDataException {
        return null;
    }

    @Override
    public URI uri(Object path) {
        return null;
    }

    @Override
    public String relativePath(Object path) {
        return null;
    }

    @Override
    public ConfigurableFileCollection files(Object... paths) {
        return null;
    }

    @Override
    public ConfigurableFileCollection files(Object paths, Closure configureClosure) {
        return null;
    }

    @Override
    public ConfigurableFileCollection files(Object paths, Action<? super ConfigurableFileCollection> configureAction) {
        return null;
    }

    @Override
    public ConfigurableFileTree fileTree(Object baseDir) {
        return null;
    }

    @Override
    public ConfigurableFileTree fileTree(Object baseDir, Closure configureClosure) {
        return null;
    }

    @Override
    public ConfigurableFileTree fileTree(Object baseDir, Action<? super ConfigurableFileTree> configureAction) {
        return null;
    }

    @Override
    public ConfigurableFileTree fileTree(Map<String, ?> args) {
        return null;
    }

    @Override
    public FileTree zipTree(Object zipPath) {
        return null;
    }

    @Override
    public FileTree tarTree(Object tarPath) {
        return null;
    }

    @Override
    public <T> Provider<T> provider(Callable<T> value) {
        return null;
    }

    @Override
    public <T> PropertyState<T> property(Class<T> clazz) {
        return null;
    }

    @Override
    public ProviderFactory getProviders() {
        return null;
    }

    @Override
    public ObjectFactory getObjects() {
        return null;
    }

    @Override
    public ProjectLayout getLayout() {
        return null;
    }

    @Override
    public File mkdir(Object path) {
        return null;
    }

    @Override
    public boolean delete(Object... paths) {
        return false;
    }

    @Override
    public WorkResult delete(Action<? super DeleteSpec> action) {
        return null;
    }

    @Override
    public org.gradle.process.ExecResult javaexec(Closure closure) {
        return null;
    }

    @Override
    public org.gradle.process.ExecResult javaexec(Action<? super JavaExecSpec> action) {
        return null;
    }

    @Override
    public org.gradle.process.ExecResult exec(Closure closure) {
        return null;
    }

    @Override
    public org.gradle.process.ExecResult exec(Action<? super org.gradle.process.ExecSpec> action) {
        return null;
    }

    @Override
    public String absoluteProjectPath(String path) {
        return null;
    }

    @Override
    public String relativeProjectPath(String path) {
        return null;
    }

    @Override
    public AntBuilder getAnt() {
        return null;
    }

    @Override
    public AntBuilder createAntBuilder() {
        return null;
    }

    @Override
    public AntBuilder ant(Closure configureClosure) {
        return null;
    }

    @Override
    public AntBuilder ant(Action<? super AntBuilder> configureAction) {
        return null;
    }

    @Override
    public ConfigurationContainer getConfigurations() {
        return null;
    }

    @Override
    public void configurations(Closure configureClosure) {

    }

    @Override
    public ArtifactHandler getArtifacts() {
        return null;
    }

    @Override
    public void artifacts(Closure configureClosure) {

    }

    @Override
    public void artifacts(Action<? super ArtifactHandler> configureAction) {

    }

    @Override
    public Convention getConvention() {
        return null;
    }

    @Override
    public int depthCompare(Project otherProject) {
        return 0;
    }

    @Override
    public int getDepth() {
        return 0;
    }

    @Override
    public TaskContainer getTasks() {
        return null;
    }

    @Override
    public void subprojects(Action<? super Project> action) {

    }

    @Override
    public void subprojects(Closure configureClosure) {

    }

    @Override
    public void allprojects(Action<? super Project> action) {

    }

    @Override
    public void allprojects(Closure configureClosure) {

    }

    @Override
    public void beforeEvaluate(Action<? super Project> action) {

    }

    @Override
    public void afterEvaluate(Action<? super Project> action) {

    }

    @Override
    public void beforeEvaluate(Closure closure) {

    }

    @Override
    public void afterEvaluate(Closure closure) {

    }

    @Override
    public boolean hasProperty(String propertyName) {
        return false;
    }

    @Override
    public Map<String, ?> getProperties() {
        return null;
    }

    @Override
    public Object property(String propertyName) throws MissingPropertyException {
        return null;
    }

    @Nullable
    @Override
    public Object findProperty(String propertyName) {
        return null;
    }

    @Override
    public Gradle getGradle() {
        return null;
    }

    @Override
    public LoggingManager getLogging() {
        return null;
    }

    @Override
    public Object configure(Object object, Closure configureClosure) {
        return null;
    }

    @Override
    public Iterable<?> configure(Iterable<?> objects, Closure configureClosure) {
        return null;
    }

    @Override
    public <T> Iterable<T> configure(Iterable<T> objects, Action<? super T> configureAction) {
        return null;
    }

    @Override
    public RepositoryHandler getRepositories() {
        return null;
    }

    @Override
    public void repositories(Closure configureClosure) {

    }

    @Override
    public DependencyHandler getDependencies() {
        return null;
    }

    @Override
    public void dependencies(Closure configureClosure) {

    }

    @Override
    public ScriptHandler getBuildscript() {
        return null;
    }

    @Override
    public void buildscript(Closure configureClosure) {

    }

    @Override
    public WorkResult copy(Closure closure) {
        return null;
    }

    @Override
    public WorkResult copy(Action<? super CopySpec> action) {
        return null;
    }

    @Override
    public CopySpec copySpec(Closure closure) {
        return null;
    }

    @Override
    public CopySpec copySpec(Action<? super CopySpec> action) {
        return null;
    }

    @Override
    public CopySpec copySpec() {
        return null;
    }

    @Override
    public WorkResult sync(Action<? super CopySpec> action) {
        return null;
    }

    @Override
    public ProjectState getState() {
        return null;
    }

    @Override
    public <T> NamedDomainObjectContainer<T> container(Class<T> type) {
        return null;
    }

    @Override
    public <T> NamedDomainObjectContainer<T> container(Class<T> type, NamedDomainObjectFactory<T> factory) {
        return null;
    }

    @Override
    public <T> NamedDomainObjectContainer<T> container(Class<T> type, Closure factoryClosure) {
        return null;
    }

    @Override
    public ExtensionContainer getExtensions() {
        return null;
    }

    @Override
    public ResourceHandler getResources() {
        return null;
    }

    @Override
    public SoftwareComponentContainer getComponents() {
        return null;
    }

    @Override
    public InputNormalizationHandler getNormalization() {
        return null;
    }

    @Override
    public void normalization(Action<? super InputNormalizationHandler> configuration) {

    }
}
