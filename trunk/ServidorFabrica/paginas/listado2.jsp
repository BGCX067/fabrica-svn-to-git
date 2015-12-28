
<layout:html>
	<layout:form action="myAction" styleClass="FORM">
		<layout:row>
			<layout:column>
				<layout:text key="search.name" property="name" styleClass="FIELD"/>
				<layout:text key="search.town" property="town" styleClass="FIELD"/>
				<layout:submit>
					<layout:message key="search.submit"/>
				</layout:submit>
			</layout:column>
			<layout:collection name="list" styleClass="ARRAY">
				<layout:collectionItem title="person.firstname" property="firstname"/>
				<layout:collectionItem title="person.lastname" property="lastname"/>
				<layout:collectionItem title="person.street" property="street"/>
				<layout:collectionItem title="person.town" property="town"/>
			</layout:collection>
		</layout:row>
	</layout:form>
</layout:html>
