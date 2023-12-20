{{/*
Defines common labels
*/}}
{{- define "labels" }}
labels:
  version: {{ .Chart.Version | quote }}
  date: {{ now | htmlDate }}
{{- end }}
{{- define "dbProbes" }}
startupProbe:
  exec:
    command: ["pg_isready", "-U", "rsuser", "-d", "$(POSTGRES_DB)"]
  initialDelaySeconds: 10
  periodSeconds: 5
  failureThreshold: 15
readinessProbe:
  exec:
    command:
      - pg_isready
      - -h
      - localhost
      - -p
      - "5432"
  initialDelaySeconds: 10
  periodSeconds: 5
  failureThreshold: 15
livenessProbe:
  exec:
    command: ["pg_isready", "-U", "rsuser", "-d", "$(POSTGRES_DB)"]
  initialDelaySeconds: 15
  periodSeconds: 10
  failureThreshold: 3
{{- end }}
{{- define "serviceProbes" }}
startupProbe:
  httpGet:
    path: /actuator/health
    port: 8080
  initialDelaySeconds: 25
  periodSeconds: 5
  failureThreshold: 5
readinessProbe:
  httpGet:
    path: /actuator/health/readiness
    port: 8080
  initialDelaySeconds: 25
  periodSeconds: 15
livenessProbe:
  httpGet:
    path: /actuator/health/liveness
    port: 8080
  initialDelaySeconds: 25
  periodSeconds: 10
  failureThreshold: 3
{{- end }}