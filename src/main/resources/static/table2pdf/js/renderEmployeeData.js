function renderEMployee(
	data,
	totalEPFContributionRemitted,
	totalEPSContributionRemitted,
	totalEPFEPSContributionRemitted,
	totalNumber,
	footerFunc
) {
	const html = `<div class="flex flex-col">
      <div class="flex flex-col text-[28px]" style="font-weight:350">
        <div class="flex h-[55.5px]">
          <div class="w-[522px] border border-black flex pl-[10px] items-center">
            Name of Establishment
          </div>
          <div class="w-[1705px] border border-black border-l-0 flex pl-[10px] items-center">
            ${data.corporateName || ''}
          </div>
        </div>
        <div class="flex h-[55px]">
          <div class="w-[522px] border pl-[10px] border-black border-t-0 flex items-center">
            Establishment Id
          </div>
          <div class="w-[611px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            ${data.establishmentId || ''}
          </div>
          <div class="w-[483px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">LIN</div>
          <div class="w-[611px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            ${data.lin || ''}
          </div>
        </div>
        <div class="flex h-[55.5px]">
          <div class="w-[522px] border pl-[10px] border-black border-t-0 flex items-center">Wage Month</div>
          <div class="w-[611px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            ${data.wageMonth || ''}
          </div>
          <div class="w-[483px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            Return Month
          </div>
          <div class="w-[611px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            ${data.returnMonth || ''}
          </div>
        </div>
        <div class="flex h-[55.5px]">
          <div class="w-[522px] border pl-[10px] border-black border-t-0 flex items-center">
            Contribution Rate (%)
          </div>
          <div class="w-[611px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">12</div>
          <div class="w-[483px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            ECR Type
          </div>
          <div class="w-[611px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">ECR</div>
        </div>
        <div class="flex h-[55.5px]">
          <div class="w-[522px] border pl-[10px] border-black border-t-0 flex items-center">
            Salary Disbursement Date
          </div>
          <div class="w-[611px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            ${data.salaryDisbursementDate || ''}
          </div>
          <div class="w-[483px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            Uploaded Date Time
          </div>
          <div class="w-[611px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            ${data.uploadedDateTime || ''}
          </div>
        </div>
        <div class="flex h-[55.5px]">
          <div class="w-[522px] border pl-[10px] border-black border-t-0 flex items-center">
            Exemption Status
          </div>
          <div class="w-[611px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            ${data.exemptionStatus || ''}
          </div>
          <div class="w-[483px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            TRRN Number
          </div>
          <div class="w-[611px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            ${data.trrnNumber || ''}
          </div>
        </div>
        <div class="flex h-[55.5px]">
          <div class="w-[522px] border pl-[10px] border-black border-t-0 flex items-center">Remarks</div>
          <div class="w-[611px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            ${data.remarks || ''}
          </div>
          <div class="w-[483px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            ECR Id
          </div>
          <div class="w-[611px] border pl-[10px] border-black border-l-0 border-t-0 flex items-center">
            ${data.ecrId || ''}
          </div>
        </div>
        <div class="flex h-[55px]">
          <div class="w-[522px] border pl-[12px] border-black border-t-0 border-b-0 flex items-center">
            Total Members
          </div>
          <div class="w-[611px] border pl-[10px] border-black border-l-0 border-t-0 border-b-0 flex items-center">${
						totalNumber || ''
					}</div>
          <div class="w-[483px] border pl-[10px] border-black border-l-0 border-t-0 border-b-0 flex items-center">
           ${data.aadhaarNotSeededMember ? 'Aadhaar Not Seeded Member' : ''}
          </div>
          <div class="w-[605px] border-none-r pl-[10px] border-black flex items-center" style="margin-left:5px;">
          ${data.aadhaarNotSeededMember || ''}
          </div>
          <div class="w-[4px] h-[55px] border-none-l border-black">

          </div>
        </div>
        <div class="flex h-[56.5px]">
          <div class="w-[2227px] border pl-[10px] font-bold border-black flex items-center">
            Contribution and Remittance Details (In Rupees) :
          </div>
        </div>
        <div class="flex h-[55.5px]">
          <div class="w-[522px] border pl-[10px] border-black border-t-0 flex items-center">
            Total EPF Contribution Remitted
          </div>
          <div
            class="w-[611px] border pr-[12px] border-black border-l-0 border-t-0 flex justify-end items-center"
          >
          ${totalEPFContributionRemitted || ''}
          </div>
          <div class="w-[483px] border pl-[12px] border-black border-l-0 border-t-0 flex items-center">
            Total EPS Contribution Remitted
          </div>
          <div
            class="w-[611px] border pr-[12px] border-black border-l-0 border-t-0 flex justify-end items-center"
          >
            ${totalEPSContributionRemitted || ''}
          </div>
        </div>
        <div class="flex h-[55.5px]">
          <div class="w-[522px] border pl-[10px] border-black border-t-0 flex items-center">
            Total EPF-EPS Contribution Remitted
          </div>
          <div
            class="w-[611px] border pr-[12px] border-black border-l-0 border-t-0 flex justify-end items-center"
          >
          ${totalEPFEPSContributionRemitted || ''}
          </div>
          <div class="w-[483px] border pl-[12px] border-black border-l-0 border-t-0 flex items-center">
            Total Refund Advance
          </div>
          <div
            class="w-[611px] border pr-[12px] border-black  border-l-0 border-t-0 flex justify-end items-center"
          >
            ${data.totalRefundAdvance}
          </div>
        </div>
        <div class="flex h-[56px]">
          <div class="w-[2227px] border pl-[10px] font-bold border-black border-t-0 flex items-center">
            PMRPY Upfront Benefit Details (In Rupees) :
          </div>
        </div>
        <div class="flex h-[56px]">
          <div class="w-[522px] border pl-[10px] border-black border-t-0 flex items-center">
            Total PMRPY Upfront EPF Amount
          </div>
          <div
            class="w-[611px] border pr-[12px] border-black   border-l-0 border-t-0 flex justify-end items-center"
          >
            0
          </div>
          <div class="w-[483px] border pl-[12px] border-black   border-l-0 border-t-0 flex items-center">
            Total PMRPY Upfront EPS Amount
          </div>
          <div
            class="w-[611px] border pr-[12px] border-black   border-l-0 border-t-0 flex justify-end items-center"
          >
            0
          </div>
        </div>
        <div class="flex h-[55.5px]">
          <div class="w-[522px] border pl-[10px] border-black border-t-0 flex items-center">
            PMRPY benefit remarks
          </div>
          <div class="w-[1705px] border pl-[10px] border-black   border-l-0 border-t-0 flex items-center">NA</div>
        </div>
        <div class="flex h-[55.5px]">
          <div class="w-[2227px] border pl-[10px] font-bold border-black border-t-0 flex items-center">
            ABRY Upfront Benefit Details (In Rupees) :
          </div>
        </div>
        <div class="flex h-[108px]">
          <div class="w-[522px] border pl-[10px] border-black border-t-0 flex items-center">
            Total ABRY benefit Amount
          </div>
          <div class="flex w-[1705px] flex-col">
            <div class="flex h-[53.5px]">
              <div class="w-[611px] border border-black border-l-0 border-t-0 flex justify-center items-center pl-[10px]">
                Employee EPF Share
              </div>
              <div class="w-[483px] border border-black border-l-0 border-t-0 justify-center flex items-center pl-[10px]">
                Employer EPS Share
              </div>
              <div class="w-[611px] border border-black border-l-0 border-t-0 flex justify-center items-center pl-[10px]">
                Employer EPF Share
              </div>
            </div>
            <div class="flex h-[56px]">
              <div
                class="w-[611px] border pr-[12px] border-black border-t-0 border-l-0 border-t-0 flex justify-end items-center"
              >
                ${data.employeeEPFShare || ''}
              </div>
              <div
                class="w-[483px] border pr-[12px] border-black border-t-0 border-l-0 border-t-0 justify-end flex items-center"
              >
                ${data.employerEPSShare || ''}
              </div>
              <div
                class="w-[611px] border pr-[12px] border-black border-t-0 border-l-0 border-t-0 flex justify-end items-center"
              >
                ${data.employerEPFShare}
              </div>
            </div>
          </div>
        </div>
        <div class="flex h-[64px]">
          <div class="w-[522px] border pl-[10px] border-black border-t-0 flex items-center">
            ABRY benefit remarks
          </div>
          <div
            class="w-[1705px] border pl-[10px] leading-[28px] border-black border-l-0 border-t-0 flex items-center"
          >
            ${data.abryBenefitRemarks || ''}
          </div>
        </div>
      </div>
    </div>
    ${footerFunc(1)}
    `

	document.querySelector('.employeeData').innerHTML = html
}

