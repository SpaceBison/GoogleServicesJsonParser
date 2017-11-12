package org.spacebison.googleservicesjsonparser;

import org.gradle.api.Action;
import org.gradle.api.AntBuilder;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.NamedDomainObjectFactory;
import org.gradle.api.PathValidation;
import org.gradle.api.Project;
import org.gradle.api.ProjectEvaluationListener;
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
import org.gradle.api.internal.GradleInternal;
import org.gradle.api.internal.artifacts.Module;
import org.gradle.api.internal.file.FileResolver;
import org.gradle.api.internal.initialization.ClassLoaderScope;
import org.gradle.api.internal.plugins.ExtensionContainerInternal;
import org.gradle.api.internal.plugins.PluginManagerInternal;
import org.gradle.api.internal.project.ProjectIdentifier;
import org.gradle.api.internal.project.ProjectInternal;
import org.gradle.api.internal.project.ProjectRegistry;
import org.gradle.api.internal.project.ProjectStateInternal;
import org.gradle.api.internal.tasks.TaskContainerInternal;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.LoggingManager;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.plugins.Convention;
import org.gradle.api.plugins.ObjectConfigurationAction;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.provider.PropertyState;
import org.gradle.api.provider.Provider;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.resources.ResourceHandler;
import org.gradle.api.tasks.WorkResult;
import org.gradle.configuration.ConfigurationTargetIdentifier;
import org.gradle.configuration.project.ProjectConfigurationActionContainer;
import org.gradle.groovy.scripts.ScriptSource;
import org.gradle.internal.logging.StandardOutputCapture;
import org.gradle.internal.metaobject.DynamicObject;
import org.gradle.internal.service.ServiceRegistry;
import org.gradle.internal.service.scopes.ServiceRegistryFactory;
import org.gradle.model.internal.registry.ModelRegistry;
import org.gradle.normalization.InputNormalizationHandler;
import org.gradle.process.ExecResult;
import org.gradle.process.ExecSpec;
import org.gradle.process.JavaExecSpec;
import org.gradle.util.Path;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.Nullable;

import groovy.lang.Closure;
import groovy.lang.MissingPropertyException;
import groovy.lang.Script;

class NullProjectInternal implements ProjectInternal {
    @Override
    public ProjectInternal getParent() {
        return null;
    }

    @Override
    public ProjectInternal getRootProject() {
        return null;
    }

    @Override
    public Project evaluate() {
        return null;
    }

    @Override
    public ProjectInternal bindAllModelRules() {
        return null;
    }

    @Override
    public TaskContainerInternal getTasks() {
        return null;
    }

    @Override
    public ScriptSource getBuildScriptSource() {
        return null;
    }

    @Override
    public void addChildProject(ProjectInternal childProject) {

    }

    @Override
    public ProjectInternal project(String path) throws UnknownProjectException {
        return null;
    }

    @Override
    public ProjectInternal findProject(String path) {
        return null;
    }

    @Override
    public ProjectRegistry<ProjectInternal> getProjectRegistry() {
        return null;
    }

    @Override
    public DynamicObject getInheritedScope() {
        return null;
    }

    @Override
    public GradleInternal getGradle() {
        return null;
    }

    @Override
    public ProjectEvaluationListener getProjectEvaluationBroadcaster() {
        return null;
    }

    @Override
    public FileResolver getFileResolver() {
        return null;
    }

    @Override
    public ServiceRegistry getServices() {
        return null;
    }

    @Override
    public ServiceRegistryFactory getServiceRegistryFactory() {
        return null;
    }

    @Override
    public StandardOutputCapture getStandardOutputCapture() {
        return null;
    }

    @Override
    public ProjectStateInternal getState() {
        return null;
    }

    @Override
    public ExtensionContainerInternal getExtensions() {
        return null;
    }

    @Override
    public ProjectConfigurationActionContainer getConfigurationActions() {
        return null;
    }

    @Override
    public ModelRegistry getModelRegistry() {
        return null;
    }

    @Override
    public ClassLoaderScope getClassLoaderScope() {
        return null;
    }

    @Override
    public ClassLoaderScope getBaseClassLoaderScope() {
        return null;
    }

    @Override
    public void setScript(Script script) {

    }

    @Override
    public void addDeferredConfiguration(Runnable configuration) {

    }

    @Override
    public void fireDeferredConfiguration() {

    }

    @Override
    public Path getProjectPath() {
        return null;
    }

    @Override
    public Path getIdentityPath() {
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
    public File file(Object path, PathValidation validation) {
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
    public ExecResult javaexec(Closure closure) {
        return null;
    }

    @Override
    public ExecResult javaexec(Action<? super JavaExecSpec> action) {
        return null;
    }

    @Override
    public ExecResult exec(Closure closure) {
        return null;
    }

    @Override
    public ExecResult exec(Action<? super ExecSpec> action) {
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
    public Logger getLogger() {
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

    @Override
    public int compareTo(Project project) {
        return 0;
    }

    @Override
    public Path identityPath(String name) {
        return null;
    }

    @Override
    public Path projectPath(String name) {
        return null;
    }

    @Override
    public Module getModule() {
        return null;
    }

    @Override
    public PluginManagerInternal getPluginManager() {
        return null;
    }

    @Override
    public ConfigurationTargetIdentifier getConfigurationTargetIdentifier() {
        return null;
    }

    @Override
    public ProjectIdentifier getParentIdentifier() {
        return null;
    }

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
}
